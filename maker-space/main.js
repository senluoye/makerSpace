// axios.defaults.baseURL = '/api';
axios.defaults.headers.token=""|| sessionStorage.getItem('token');

// function hashChangeFire() {
//     //url改变，调用逻辑
//     alert(414546);
// }
// // 判断浏览器是否支持onhashchange事件
// if (('onhashchange' in window) && ((typeof document.documentMode === 'undefined') || document.documentMode == 8)) {
//     window.onhashchange = hashChangeFire;  
// } else {
//     // 如浏览器不支持onhashchange事件，则用定时器检测的办法
//     setInterval(function () {
//         // isHashChanged() 为要检测url是否被改变的函数
//         var ischanged = isHashChanged();
//         if (ischanged) {
//             hashChangeFire(); //如被改变，设用函数
//         }
//     }, 150);
// }

var app = new Vue({
    el: "#app",
    data: {
        test:1,
    message: 123,
    isShow:false,
    isImport:false,
    id:0,//指定管理员id
    name:""|| sessionStorage.getItem('name'),
    password:""|| sessionStorage.getItem('password'),
    // api请求
    token:""|| sessionStorage.getItem('token'),
    method:'get',
    url:'',
    data:[],
    res:{},
    //某个企业团队详情
    enterpriseDetail:{
        teamName:""|| sessionStorage.getItem('teamName'),	//企业/团队名称
        head:""|| sessionStorage.getItem('head'),	//企业负责人
        phone:""|| sessionStorage.getItem('phone'),		//联系方式
        joinTime:""|| sessionStorage.getItem('joinTime'),	//入驻众创空间时间
        teamNumber:""|| sessionStorage.getItem('teamNumber'),	//人数
        characteristic:""|| sessionStorage.getItem('characteristic'),	//主要负责人创业特征
        kind:""|| sessionStorage.getItem('kind'),	//行业类别
        field:""|| sessionStorage.getItem('field'),		//技术领域
        achievements:""|| sessionStorage.getItem('achievements'),	//获奖成果
        achievements:""|| sessionStorage.getItem('scope'),		//经营范围
        income:""|| sessionStorage.getItem('income'),	//总收入
        tax:""|| sessionStorage.getItem('tax'),	//上缴税额
        preferentialTax:false|| sessionStorage.getItem('preferentialTax'),	//享受税收优惠政策 (是：true 否：false)
        taxFree:""|| sessionStorage.getItem('taxFree'),	//免税金额 (是：具体金额 否：null)
        support:false|| sessionStorage.getItem('support'),	//获得财政资金支持
        supportAmount:""|| sessionStorage.getItem('supportAmount'),	    //支持金额 
        riskInvestment:false|| sessionStorage.getItem('riskInvestment'),	//获天使或风险投资额
        investmentAmount:""|| sessionStorage.getItem('investmentAmount'),	//投融资总额
        cooperation:false|| sessionStorage.getItem('cooperation'),	//是否参与校企合作
        projectName:""|| sessionStorage.getItem('projectName'),	//合作项目名称
        projectAmount:""|| sessionStorage.getItem('projectAmount'),		//申报项目金额
        highTec:false|| sessionStorage.getItem('highTec'),	//是否高新技术企业
        tecSme:false|| sessionStorage.getItem('tecSme'),	//是否是科技型中小企业
    }||sessionStorage.getItem('enterpriseDetail'),
    enterprise:{
        enterpriseId:""||sessionStorage.getItem('enterpriseId'),          //企业/团队的唯一ID
        teamName:""||sessionStorage.getItem('teamName'),				//表示企业/团队的名称
        propertyId:""||sessionStorage.getItem('propertyId'),           //在孵企业知识产权情况表唯一Id
        activityId:""||sessionStorage.getItem('activityId')               //活动情况表唯一ID
    },
        // 提交某个企业/团队从业人员详细情况
        employeeDetail:{
            enterpriseId:""||sessionStorage.getItem('enterpriseId'),			//在孵企业知识产权情况表唯一Id
            employees:0||sessionStorage.getItem('employees'),				//在孵企业/团队从业人员
            doctor:0||sessionStorage.getItem('doctor'),				//博士
            master:0||sessionStorage.getItem('master'),				//硕士
            graduate:0||sessionStorage.getItem('graduate'),				//研究生学历
            bachelor:0||sessionStorage.getItem('bachelor'),				//本科学历
            college:0||sessionStorage.getItem('college'),				//大专学历
            tecSecondary:0||sessionStorage.getItem('tecSecondary'),			//中专学历
            tecActivists:0||sessionStorage.getItem('tecActivists'),			//科技活动人员
            radNumber:0||sessionStorage.getItem('radNumber'),			//研究与试验发展(R&D)人员
            returnees:0||sessionStorage.getItem('returnees'),			//留学回国人员
            talents:0||sessionStorage.getItem('talents'),				//千人计划人数
            trainee:0||sessionStorage.getItem('trainee'),				//接纳大学生、研究生实习人员(人/天)
            employment:0||sessionStorage.getItem('employment'),			//接纳应届毕业生就业人员
        },
         // 获取某个企业/团队从业人员详细情况
        employeeGet:{
            employeeId:""||sessionStorage.getItem('employeeId'),		//企业/团队从业情况表唯一ID
            employees:0||sessionStorage.getItem('employees'),				//在孵企业/团队从业人员
            doctor:0||sessionStorage.getItem('doctor'),				//博士
            master:0||sessionStorage.getItem('master'),				//硕士
            graduate:0||sessionStorage.getItem('graduate'),				//研究生学历
            bachelor:0||sessionStorage.getItem('bachelor'),				//本科学历
            college:0||sessionStorage.getItem('college'),				//大专学历
            tecSecondary:0||sessionStorage.getItem('tecSecondary'),			//中专学历
            tecActivists:0||sessionStorage.getItem('tecActivists'),			//科技活动人员
            radNumber:0||sessionStorage.getItem('radNumber'),			//研究与试验发展(R&D)人员
            returnees:0||sessionStorage.getItem('returnees'),			//留学回国人员
            talents:0||sessionStorage.getItem('talents'),				//千人计划人数
            trainee:0||sessionStorage.getItem('trainee'),				//接纳大学生、研究生实习人员(人/天)
            employment:0||sessionStorage.getItem('employment'),			//接纳应届毕业生就业人员
        },
        // 获取某个在孵企业知识产权详细情况
      
            // propertyID:""||sessionStorage.getItem('propertyID'),			//在孵企业知识产权情况表唯一Id
            // applications:0||sessionStorage.getItem('applications'),				//当年知识产权申请数
            // applicationsPatent:0||sessionStorage.getItem('applicationsPatent'),		//当年知识产权申请数中的专利发明数
            // patentNum:""||sessionStorage.getItem('patentNum'),				//当年知识产权申请数中专利发明对应的专利号
            // softNum:""||sessionStorage.getItem('softNum'),				//当年知识产权申请数中专利发明对应的软著登记号
            // trademarkNum:""||sessionStorage.getItem('trademarkNum'),			//当年知识产权申请数中专利发明对应的商标号
            // granted:0||sessionStorage.getItem('granted'),					//当年知识产权授权数
            // grantedPatent:0||sessionStorage.getItem('grantedPatent'),			//当年知识产权授权数中的发明专利数
            // valid:0||sessionStorage.getItem('valid'),					//拥有有效知识产权数
            // validPatent:0||sessionStorage.getItem('validPatent'),				//拥有有效知识产权数中的发明专利数
            // softCopyright:0||sessionStorage.getItem('softCopyright'),			//拥有有效知识产权数中的软件著作权数
            // plantVariety:0||sessionStorage.getItem('plantVariety'),				//拥有有效知识产权数中的植物新品种（神奇的字段）数
            // icLayout:0||sessionStorage.getItem('icLayout'),					//拥有有效知识产权数中的集成电路布图数
            // foreignPatents:0||sessionStorage.getItem('foreignPatents'),			//购买国外技术专利
            // contractTransaction:0||sessionStorage.getItem('contractTransaction'),		//技术合同交易
            // contractTurnover:"<Long>",		//技术合同交易额
            // projectNum:0||sessionStorage.getItem('projectNum'),				//当年承担国家级科技计划项目数
            // awards:0||sessionStorage.getItem('awards'),					//当年获得省级以上奖励
            propertyGet:{
            propertyID:""||sessionStorage.getItem('propertyID'),			//在孵企业知识产权情况表唯一Id
            applications:0||sessionStorage.getItem('applications'),				//当年知识产权申请数
            applicationsPatent:0||sessionStorage.getItem('applicationsPatent'),		//当年知识产权申请数中的专利发明数
            patentNum:""||sessionStorage.getItem('patentNum'),				//当年知识产权申请数中专利发明对应的专利号
            softNum:""||sessionStorage.getItem('softNum'),				//当年知识产权申请数中专利发明对应的软著登记号
            trademarkNum:""||sessionStorage.getItem('trademarkNum'),			//当年知识产权申请数中专利发明对应的商标号
            granted:0||sessionStorage.getItem('granted'),					//当年知识产权授权数
            grantedPatent:0||sessionStorage.getItem('grantedPatent'),			//当年知识产权授权数中的发明专利数
            valid:0||sessionStorage.getItem('valid'),					//拥有有效知识产权数
            validPatent:0||sessionStorage.getItem('validPatent'),				//拥有有效知识产权数中的发明专利数
            softCopyright:0||sessionStorage.getItem('softCopyright'),			//拥有有效知识产权数中的软件著作权数
            plantVariety:0||sessionStorage.getItem('plantVariety'),				//拥有有效知识产权数中的植物新品种（神奇的字段）数
            icLayout:0||sessionStorage.getItem('icLayout'),					//拥有有效知识产权数中的集成电路布图数
            foreignPatents:0||sessionStorage.getItem('foreignPatents'),			//购买国外技术专利
            contractTransaction:0||sessionStorage.getItem('contractTransaction'),		//技术合同交易
            contractTurnover:0||sessionStorage.getItem('contractTurnover'),		//技术合同交易额
            projectNum:0||sessionStorage.getItem('projectNum'),				//当年承担国家级科技计划项目数
            awards:0||sessionStorage.getItem('awards'),					//当年获得省级以上奖励
        },
        //提交某个在孵企业知识产权详细情况
        propertyDetail:{
            enterpriseId:""||sessionStorage.getItem('enterpriseId'),			//在孵企业知识产权情况表唯一Id
            applications:0||sessionStorage.getItem('applications'),				//当年知识产权申请数
            applicationsPatent:0||sessionStorage.getItem('applicationsPatent'),		//当年知识产权申请数中的专利发明数
            patentNum:""||sessionStorage.getItem('patentNum'),				//当年知识产权申请数中专利发明对应的专利号
            softNum:""||sessionStorage.getItem('softNum'),				//当年知识产权申请数中专利发明对应的软著登记号
            trademarkNum:""||sessionStorage.getItem('trademarkNum'),			//当年知识产权申请数中专利发明对应的商标号
            granted:0||sessionStorage.getItem('granted'),					//当年知识产权授权数
            grantedPatent:0||sessionStorage.getItem('grantedPatent'),			//当年知识产权授权数中的发明专利数
            valid:0||sessionStorage.getItem('valid'),					//拥有有效知识产权数
            validPatent:0||sessionStorage.getItem('validPatent'),				//拥有有效知识产权数中的发明专利数
            softCopyright:0||sessionStorage.getItem('softCopyright'),			//拥有有效知识产权数中的软件著作权数
            plantVariety:0||sessionStorage.getItem('plantVariety'),				//拥有有效知识产权数中的植物新品种（神奇的字段）数
            icLayout:0||sessionStorage.getItem('icLayout'),					//拥有有效知识产权数中的集成电路布图数
            foreignPatents:0||sessionStorage.getItem('foreignPatents'),			//购买国外技术专利
            contractTransaction:0||sessionStorage.getItem('contractTransaction'),		//技术合同交易
            contractTurnover:"<Long>",		//技术合同交易额
            projectNum:0||sessionStorage.getItem('projectNum'),				//当年承担国家级科技计划项目数
            awards:0||sessionStorage.getItem('awards'),					//当年获得省级以上奖励
        },
        // 获取某个在孵企业科技活动详细情况
      
        activityGet:{
            activityId:""||sessionStorage.getItem('activityId'),			//活动情况表唯一ID
            underProjects:""||sessionStorage.getItem('underProjects'),			//承担各类计划项目
            nationalProject:""||sessionStorage.getItem('nationalProject'),			//国家级项目
            expenditure:""||sessionStorage.getItem('expenditure'),				//科技活动经费支出总额
            radExpenditure:""||sessionStorage.getItem('radExpenditure'),			//研究与试验发展（R&D）经费支出 (千元)
            productExpenditure:""||sessionStorage.getItem('productExpenditure'),		//新产品开发经费支出
            govermentGrant:""||sessionStorage.getItem('govermentGrant'),			//政府拨款
            selfRaised:""||sessionStorage.getItem('selfRaised'),				//企业自筹
        },
        // 提交某个在孵企业科技活动详细情况
        activityDetail:{
            enterpriseId:""||sessionStorage.getItem('enterpriseId'),			//在孵企业知识产权情况表唯一Id
            underProjects:""||sessionStorage.getItem('underProjects'),			//承担各类计划项目
            nationalProject:""||sessionStorage.getItem('nationalProject'),			//国家级项目
            expenditure:""||sessionStorage.getItem('expenditure'),				//科技活动经费支出总额
            radExpenditure:""||sessionStorage.getItem('radExpenditure'),			//研究与试验发展（R&D）经费支出 (千元)
            productExpenditure:""||sessionStorage.getItem('productExpenditure'),		//新产品开发经费支出
            govermentGrant:""||sessionStorage.getItem('govermentGrant'),			//政府拨款
            selfRaised:""||sessionStorage.getItem('selfRaised'),				//企业自筹
        },
    },
    created() {
        this.getAllEnterprise();

    },
    methods: {
        //test
        putFormFour(){
            this.addActivity();
        },
        putFormThree(){
            console.log(13213);
            this.addProperty();
            // axios({
            //     method:'post',
            //     url:"/api/property?"+this.token,
            //     data:{
            //         enterpriseId:this.enterprise.enterpriseId,			//在孵企业知识产权情况表唯一Id
            //         applications:this.applications,				//当年知识产权申请数
            //         applicationsPatent:this.applicationsPatent,		//当年知识产权申请数中的专利发明数
            //         patentNum:this.patentNum,				//当年知识产权申请数中专利发明对应的专利号
            //         softNum:this.softNum,				//当年知识产权申请数中专利发明对应的软著登记号
            //         trademarkNum:this.trademarkNum,			//当年知识产权申请数中专利发明对应的商标号
            //         granted:this.granted,					//当年知识产权授权数
            //         grantedPatent:this.grantedPatent,			//当年知识产权授权数中的发明专利数
            //         valid:this.valid,					//拥有有效知识产权数
            //         validPatent:this.validPatent,				//拥有有效知识产权数中的发明专利数
            //         softCopyright:this.softCopyright,			//拥有有效知识产权数中的软件著作权数
            //         plantVariety:this.plantVariety,				//拥有有效知识产权数中的植物新品种（神奇的字段）数
            //         icLayout:this.icLayout,					//拥有有效知识产权数中的集成电路布图数
            //         foreignPatents:this.foreignPatents,			//购买国外技术专利
            //         contractTransaction:this.contractTransaction,		//技术合同交易
            //         contractTurnover:this.contractTurnover,		//技术合同交易额
            //         projectNum:this.projectNum,				//当年承担国家级科技计划项目数
            //         awards:this.awards,					//当年获得省级以上奖励
            //     }
            // }).then(res=>{
            //     console.log(res);
            // }).catch(err=>{
            //     console.log(err);
            // })
        },
        putFormTwo(){
            this.addEmployee();
            // axios({
            //     method:'post',
            //     url:"/api/employee?"+this.token,
            //     data:{
            //         employeeId:this.employeeId,		//企业/团队从业情况表唯一ID
            //         employees:this.employees,				//在孵企业/团队从业人员
            //         doctor:this.doctor,				//博士
            //         master:this.master,				//硕士
            //         graduate:this.graduate,				//研究生学历
            //         bachelor:this.bachelor,				//本科学历
            //         college:this.college,				//大专学历
            //         tecSecondary:this.tecSecondary,			//中专学历
            //         tecActivists:this.tecActivists,			//科技活动人员
            //         radNumber:this.radNumber,			//研究与试验发展(R&D)人员
            //         returnees:this.returnees,			//留学回国人员
            //         talents:this.talents,				//千人计划人数
            //         trainee:this.trainee,				//接纳大学生、研究生实习人员(人/天)
            //         employment:this.employment,			//接纳应届毕业生就业人员
                
            //     }
            // }).then(res=>{
            //     console.log(res);
            // }).catch(err=>{
            //     console.log(err);
            // })
        },
        putFormOne(){
            this.addEnterprise();
            // axios({
            //     method:'post',
            //     url:"/api/enterprise?"+this.token,
            //     data:{    
            //         teamName:this.teamName,	//企业/团队名称
            //         head:this.head,	//企业负责人
            //         phone:this.phone,		//联系方式
            //         joinTime:this.joinTime,	//入驻众创空间时间
            //         teamNumber:this.teamNumber,	//人数
            //         characteristic:this.characteristic,	//主要负责人创业特征
            //         kind:this.kind,	//行业类别
            //         field:this.field,		//技术领域
            //         achievements:this.achievements,	//获奖成果
            //         scope:this.scope,		//经营范围
            //         income:this.income,	//总收入
            //         tax:this.tax,	//上缴税额
            //         preferentialTax:this.preferentialTax,	//享受税收优惠政策 (是：true 否：false)
            //         taxFree:this.taxFree,	//免税金额 (是：具体金额 否：null)
            //         support:this.support,	//获得财政资金支持
            //         supportAmount:this.supportAmount,	    //支持金额 
            //         riskInvestment:this.riskInvestment,	//获天使或风险投资额
            //         investmentAmount:this.investmentAmount,	//投融资总额
            //         cooperation:this.cooperation,	//是否参与校企合作
            //         projectName:this.projectName,	//合作项目名称
            //         projectAmount:this.projectAmount,		//申报项目金额
            //         highTec:this.highTec,	//是否高新技术企业
            //         tecSme:this.tecSme,	//是否是科技型中小企业
            //     }
            // }).then(res=>{
            //     console.log(res);
            // }).catch(err=>{
            //     console.log(err);
            // })
        },
        //获取数据
        getAllData(){
            // this.getAllEnterprise();
            // this.getEnterprise();
            // this.getEmployee();
            this.getProperty();
            // this.getActivity();
        },
        // 发送获取请求
        getData(){
            axios({
                method:'get',
                url: this.url,
            }).then(res => {
                this.res=res;
                console.log(res);
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })
        },
        // 发送增加请求
        postApi(dataItem){
            // console.log(this.url);
            axios({
                method:'post',
                url:this.url,
                data:dataItem
            }).then(res => {
                this.res=res;
                console.log(res);
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })
        },
        // 发送修改请求
        putApi(dataItem){
            axios({
                method:'put',
                url: this.url+"?"+this.token,
                data: dataItem,
            }).then(res => {
                this.res=res;
                console.log(res);
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })
        },
        //发送删除请求
        deleteApi(params){
            axios({
                method:'delete',
                url: this.url+"?"+this.token,
                params:params
            }).then(res => {
                if (res.msg==="succcess") {
                    console.log("删除成功");
                } 
                // this.msg = res;
            }).catch(err => {
                 console.log("删除失败");
                console.log(err);
            })
        },

        // 企业/团队基本情况(enterprise)
        // 获取全部企业/团队的详细情况 *
        getAllEnterprise(){
            this.url="/api/enterprise";
            // this.getData();  
            axios({
                method:'get',
                url: this.url,
            }).then(res => {
                console.log(res);
                this.res=res;
                sessionStorage.setItem("enterpriseId",res.data.data[0].enterpriseId);//暂时的测试
                // this.enterprise.enterpriseId=;
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })    
        },
        // 获取某个企业/团队从业人员详细情况
        getEnterprise(index){
            // setTimeout(() => {
            //    this.url="/api/enterprise";
            //     this.getData(); 
            // }, 3000); 
            axios({
                method:"get",
                url:"/api/enterprise/"+this.enterprise.enterpriseId,
               
            }).then(res=>{
                console.log("获取某个企业/团队从业人员详细情况");
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
            // setTimeout(() => {    
            //     enterpriseId=this.res.data.enterpriseId;            //表示企业/团队表的唯一ID，也是企业/团队的唯一ID
            //     teamName=this.res.data.teamName; 					//企业/团队名称
            //     head=this.res.data.head; 				//企业负责人
            //     phone=this.res.data.phone; 						//联系方式
            //     joinTime=this.res.data.joinTime; 					//入驻众创空间时间
            //     teamNumber=this.res.data.teamNumber; 				//人数
            //     characteristic=this.res.data.characteristic; 			//主要负责人创业特征
            //     kind=this.res.data.kind; 						//行业类别
            //     field=this.res.data.field; 					//技术领域
            //     achievements=this.res.data.achievements; 				//获奖成果
            //     scope=this.res.data.scope; 						//经营范围
            //     income=this.res.data.income; 					//总收入
            //     tax=this.res.data.tax; 						//上缴税额
            //     preferentialTax=this.res.data.preferentialTax; 			//享受税收优惠政策 (是：true 否：false)
            //     taxFree=this.res.data.taxFree; 					//免税金额 (是：具体金额 否：null)
            //     support=this.res.data.support; 				//获得财政资金支持
            //     supportAmount=this.res.data.supportAmount; 				//支持金额 
            //     riskInvestment=this.res.data.riskInvestment; 			//获天使或风险投资额
            //     investmentAmount=this.res.data.investmentAmount; 			//投融资总额
            //     cooperation=this.res.data.cooperation; 				//是否参与校企合作
            //     projectName=this.res.data.projectName; 				//合作项目名称
            //     projectAmount=this.res.data.projectAmount; 				//申报项目金额
            //     highTec=this.res.data.highTec; 					//是否高新技术企业
            //     tecSme=this.res.data.tecSme; 					//是否是科技型中小企业
            //     }, 5000); 
            
        },
        // 增加某个企业/团队
        addEnterprise(){
            this.url="/api/enterprise";
            // this.postApi(this.enterpriseDetail);
            axios({
                method:'post',
                url: "/api/enterprise",
                data: this.enterpriseDetail
                
            }).then(res => {
                this.res=res;
                console.log(res);
                console.log(this.res);
                sessionStorage.setItem('enterpriseId',res.data.data.enterpriseId);
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })
            setTimeout(() => {
                if(this.res.data.msg==="success") alert("入驻成功");
                else {
                    alert("入驻失败");
                }  
            }, 3000);
        },
        // 修改某个企业/团队详细情况
        changeEnterprise(){
            this.url="/api/enterprise";
            this.putApi(this.enterpriseDetail);
            setTimeout(() => {
                if(this.res.code===-1) console.log("修改成功");
                else {
                    alert("修改失败");
                }  
            }, 3000);
        },
        // 删除某个企业/团队
        deleteEnterprise(){
            this.deleteApi(this.id);
            setTimeout(() => {
               if (this.res.msg==="succcess") {
                console.log("删除成功");
            }
            if (this.res.code===-1) {
                console.log("删除失败");
            } 
            }, 3000);
            
        },


        // 入驻企业/团队从业人员情况（employee）
        // 获取全部企业/团队从业人员的基本情况
        getAllEmployee(){
            this.url="/api/employee";
            // this.getData();
            axios({
                method:'get',
                url: this.url,
            }).then(res => {
                this.res=res;
                console.log(res);
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })
        },
        // 获取某个企业/团队从业人员的基本情况
        getEmployee(index){
            // this.getAllEmployee();
            axios({
                method:"get",
                url:"/api/employee/"+this.enterprise.enterpriseId,
               
            }).then(res=>{
                console.log("获取某个企业/团队从业人员的基本情况");
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
            // setTimeout(() => {
            //    this.url="/api/employee?enterpriseId=this.res.data["+index+"].enterpriseId"; 
            //    this.getData();
            // }, 3000);
            
        },
        // 增加某个企业/团队的从业人员情况
        addEmployee(){
            console.log(this.enterprise.enterpriseId);
            axios({
                method:'post',
                url: "/api/employee",
                data: this.employeeDetail  
            }).then(res => {
                this.res=res;
                console.log(res);
                // console.log(this.res);
                // sessionStorage.setItem('enterpriseId',res.data.data.enterpriseId);
                // this.msg = res;
            }).catch(err => {
                console.log(err);
            })
            setTimeout(() => {
                if(this.res.data.msg==="success") alert("增加成功");
                else {
                    alert("增加失败");
                }  
            }, 3000);
        },
        // 修改某个企业/团队从业人员详细情况
        changeEmployee(){
            this.url="/api/employee";
            this.putApi(this.employeeDetail);
            setTimeout(() => {
                if(this.res.msg==="success") console.log("修改成功");
                else {
                    alert("修改失败");
                }  
            }, 3000);
        },
        // 删除某个企业/团队从业人员详细情况
        deleteEmployee(){
            let params={
                employeeId:this.employeeDetail.employeeId
            }
            Object.assign(table,params);
            this.deleteApi(table);
        },
        // **在孵企业知识产权情况**（property）
        // 获取全部在孵企业知识产权基本情况
        getAllProperty(){
            this.url="/api/property";
            axios({
                method:"get",
                url:"/api/property",
             
            }).then(res=>{
                // console.log("获取某个在孵企业科技活动基本情况");
                console.log(res);
                sessionStorage.setItem("propertyId",res.data.data[0].propertyId);
            }).catch(err=>{
                console.log(err);
            })
        },
        // 获取某个在孵企业知识产权详细情况 *
        getProperty(index){
            this.getAllProperty();
            // setTimeout(() => {
            //     this.url="/api/property?propertyId=this.res.data["+index+"].propertyID";
            //     this.getData();
            // }, 3000);
            axios({
                method:"get",
                url:"/api/property/"+this.enterprise.propertyId,
              
            }).then(res=>{
                console.log("获取某个在孵企业知识产权详细情况");
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
        },
        // 增加某个在孵企业知识产权情况
        addProperty(){
            // alert(5565);
            console.log(this.propertyDetail);
            axios({
                method:'post',
                url:"/api/property",
                data:this.propertyDetail
            }).then(res=>{
                this.res=res;
                console.log(res);
                console.log(this.res);
                sessionStorage.setItem("propertyID",res.data.data);
                if (res.data.msg==='success') {
                    alert("增加成功");
                }
            }).catch(err=>{
                console.log(err);
            })
           
        },
        // 修改某个在孵企业知识产权情况
        changeEmployee(){
            this.url="/api/property";
            this.putApi(this.propertyDetail);
            setTimeout(() => {
                if(this.res.msg==="success") console.log("修改成功");
                else {
                    alert("修改失败");
                }  
            }, 3000);
        },
        // 删除某个在孵企业知识产权情况
        deleteProperty(){  
            let params={
                propertyId:this.propertyDetail.propertyID
            }
            Object.assign(table,params);
            this.deleteApi(table); 
        },

        // **在孵企业科技活动情况**（activity）
        // 获取全部在孵企业科技活动基本情况
        getAllActivity(){
            this.url="/api/activity`";
           
        },
        // 获取某个在孵企业科技活动基本情况
        getActivity(item){
            // this.getAllActivity();
            // setTimeout(() => {
            //     this.url="/api/property?activityId=this.res.activity.activityId";
            //     this.getData();
            // }, 3000);
            axios({
                method:"get",
                url:"/api/activity/"+this.enterprise.activityId,
             
            }).then(res=>{
                console.log("获取某个在孵企业科技活动基本情况");
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
            
        },
        // 增加某个在孵企业科技活动基本情况
        addActivity(){
            // this.url="/api/activity";
            // this.postApi(this.activityDetail);
            axios({
                method:'post',
                url:"/api/activity",
                data:this.activityDetail
            }).then(res=>{
                this.res=res;
                console.log(res);
                // console.log(this.res);
                sessionStorage.setItem("activityId",res.data.data);

            }).catch(err=>{
                console.log(err);
            })
            setTimeout(() => {
                if(this.res.data.msg==="success") alert("增加成功");
                else {
                    alert("增加失败");
                }  
            }, 3000);
        },
        //修改某个在孵企业科技活动基本情况
        changeActivity(){
            this.url="/api/activity";
            this.putApi(this.activityDetail);
            setTimeout(() => {
                if(this.res.msg==="success") console.log("修改成功");
                else {
                    alert("修改失败");
                }  
            }, 3000);
        },
        // 删除某个在孵企业科技活动基本情况
        deleteActivity(){  
            let params={
                activityId:this.activityDetail.activityId
            }
            Object.assign(table,params);
            this.deleteApi(table); 
        },
        //忘记密码
        forgetPwd(){
            this.isShow=!this.isShow;
        },
        // 判断登录
        login(){
            // alert("请输入账号/密码")

            if(this.name===""||this.password===""){
                alert("请输入账号/密码")
            }else{
                this.putLoginApi();
            }
        },
        // 发送登录api
        putLoginApi(){
            axios({
                method: 'post',
                url: "/api/login",
                data: {
                    name:this.name,
                    password:this.password
                }
            }).then(res => {
                if (res.data.msg==="success") {
                    // 存储token
                    sessionStorage.setItem('token',res.data.data.token);
                    alert("登录成功");
                    this.isImport=!this.isImport;
              

                }else{
                    alert("登录失败");

                }
                console.log(res);
                // this.msg = res;
            }).catch(err => {
                alert("登录失败");
                console.log(err);
            })
        }

    },
})