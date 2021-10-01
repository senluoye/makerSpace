var mybutton = document.getElementsByClassName("mybutton")[0]
var myinput = document.getElementsByClassName("myinput")
var mycheck = document.getElementsByClassName("form-check-input")
var people = document.getElementsByClassName("form-control")
var form3=document.getElementById("form3")
// mybutton.addEventListener("click", function() {
//     for (var i = 0; i < myinput.length; i++) { //输入框的值
//         console.log(myinput[i].value);
//     }
//     for (var i = 0; i < mycheck.length; i++) { //拿到check的值
//         if (mycheck[i].checked === true) {
//             console.log(mycheck[i].parentNode.childNodes[1].nextElementSibling.innerText.trim());
//         }
//     }
//     for (var i = 0; i < people.length; i++) {
//         console.log(people[i].value);
//     }
// });
// mybutton.onclick=  function (params) {
//     console.log(456);
// }
//     mybutton.onclick=$('#form3').on('submit', function() {
//         console.log(123);
//         var params = $('#form').serialize();// username=''&age=''
//         // 创建ajax对象
//         var xhr = new XMLHttpRequest();
//         // 对ajax对象进行配置
//         xhr.open('post', '/test');
//         // 当发送跨域请求时，携带cookie信息
//         xhr.withCredentials = true;
//         // 设置请求参数格式的类型（post请求必须要设置）
//         xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//         console.log(params);
//         // 发送请求并传递请求参数
//         xhr.send(params);// 
//         // 监听服务器端给予的响应内容
//         xhr.onload = function () {
//             console.log(xhr.responseText);
//         }
//        // return false//默认阻止提交数据跳转
//    })
  

// var app = new Vue({
//     el: "#app",
//     data: {
//     message: 123,
//     isShow:false,
//     isImport:false,
//     name:""|| sessionStorage.getItem('name'),
//     password:""|| sessionStorage.getItem('password'),
//     token:""|| sessionStorage.getItem('token'),
//     },
//     methods: {
//         forgetPwd(){
//             this.isShow=!this.isShow;
            
//         },
//         // 判断登录
//         login(){
//             if(this.name===""||this.password===""){
//                 alert("请输入账号/密码")
//             }else{
//                 this.putLoginApi();
//             }
//         },
//         // 发送登录api
//         putLoginApi(){
//             axios({
//                 method: 'post',
//                 url: "/api/login",
//                 data: {
//                     name:this.name,
//                     password:this.password
//                 }
//             }).then(res => {
//                 if (msg==="success") {
//                     // 存储token
//                     sessionStorage.setItem('token',res.data.token);
//                     alert("登录成功");
//                     this.isImport=!this.isImport;

//                 }else{
//                     alert("登录失败");

//                 }
//                 console.log(res);
//                 // this.msg = res;
//             }).catch(err => {
//                 alert("登录失败");
//                 console.log(err);
//             })
//         }
//     },
// })

