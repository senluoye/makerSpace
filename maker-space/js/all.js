var all = document.getElementById("all")
var deinf = document.getElementById("deinf")
var title = document.getElementById("title")
var prepage = document.getElementById("prepage")
var nextpage = document.getElementById("nextpage")
var back = document.getElementById("back")
var check = document.getElementsByClassName("form-check-input")
var xiazai = document.getElementById("xiazai")
var  load = document.getElementById("load")

back.addEventListener("click",function(){
    all.style.display="block"
    title.style.display="block"
    deinf.style.display="none"
})
var data
tbody = document.getElementsByTagName("tbody")[0]
axios({
    method:'get',
    url: "/api/enterprise",
}).then(res => {
    load.style.visibility='hidden'
    all.style.display="block"
    title.style.display="block"

    data = res.data.data
    console.log(data);
    for(var i=0;i<data.length;i++){
        var tr = document.createElement('tr')
        tr.className='detail'
        tr.value=data[i].enterpriseId//id存在value里面！！！！！！！！！！！！！！！！！！！！
        tr.innerHTML = '<th scope="row">'+(i+1)+'</th><td>'+data[i].teamName+'</td><td>'+data[i].head +'</td><td>'+ data[i].phone+'</td><td>' + data[i].joinTime+'</td><td>'+ data[i].teamNumber+'</td><td><button class="btn btn-outline-dark ">删除</button></td>';
        tbody.appendChild(tr)
    }

    var detail=document.getElementsByClassName("detail")
        data = tbody.childNodes
    for(var i=0;i<data.length;i++){
        detail[i].addEventListener("click",function(){
            var enterpriseId = this.value
            app.getAllData(enterpriseId)
            console.log(enterpriseId);
            deinf.style.display="block"
            all.style.display="none"
            title.style.display="none"

        })
    }


}).catch(err => {
    console.log(err);
})    



xiazai.addEventListener('click',function(){
 // 要导出的json数据，这部分数据可以来自ajax请求
 const jsonData = [
    {
        "name": "常健",
        "dept": "移动研发部",
        "id": "1",
        "attendance": 10,
        "rest": 20
    },
    {
        "name": "陈熙文",
        "dept": "移动研发部",
        "id": "2",
        "attendance": 20,
        "rest": 10
    },
    {
        "name": "迟野",
        "dept": "移动研发部",
        "id": "3",
        "attendance": 0,
        "rest": 30
    }
]
    //列标题，逗号隔开
    let str = `企业/团队名称,企业负责人,联系方式,入驻众创空间时间,企业/团队人数,企业/团队主要负责人创业特征,行业类别,企业所属技术领域,企业/团队获奖成果,企业经营的范围,企业/团队总收入,上缴税额,享受税收优惠政策,免税金额,获得财政资金支持,支持金额,获天使或风险投资额,投融资总额,是否参与校企合作,合作项目名称及申报项目金额,是否高新技术企业,是否是科技型中小企业,在孵企业/团队从业人员,博士,硕士,研究生学历,本科学历,大专学历,中专学历,科技活动人员,研究与试验发展(R&D)人员,留学回国人员,千人计划人数,接纳大学生、研究生实习人员,接纳应届毕业生就业人员,当年知识产权申请数,其中：发明专利,当年知识产权授权数,其中：发明专利,拥有有效知识产权数,其中：发明专利,软件著作权,植物新品种,集成电路布图,购买国外技术专利,技术合同交易数量,技术合同交易额,当年承担国家级科技计划项目数,当年获得省级以上奖励,承担各类计划项目,其中：国家级项目,科技活动经费支出总额,其中：研究与试验发展（R&D）经费支出,新产品开发经费支出,政府拨款,企业自筹\n`;
    //增加\t为了不让表格显示科学计数法或者其他格式
    for(let i = 0; i < jsonData.length; i++){
        for(let item in jsonData[i]){
            str+=`${jsonData[i][item] + '\t'},`;
        }
        str+='\n';
    }
    //encodeURIComponent解决中文乱码， \ufeff是 ""
    let uri = 'data:textv;charset=utf-8,\ufeff' + encodeURIComponent(str);
    //通过创建a标签实现
    let link = document.createElement("a");
    link.href = uri;
    //对下载的文件命名
    link.download =  "企业详情.csv";
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
})
