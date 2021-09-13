var app = new Vue({
    el: "#app",
    data: {
    message: 123,
    isShow:false,
    isImport:false
    },
    methods: {
        forgetPwd(){
            this.isShow=!this.isShow;
        },
        login(){
            this.isImport=!this.isImport;
        }
    },
})