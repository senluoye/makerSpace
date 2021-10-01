var app = new Vue({
    el: "#app",
    data: {
        msg: " ",
        dataTest: 0 || sessionStorage.getItem('dataTest'),
        num: 7,
        msgData:1,
        checkNames:"",
        checkName:"",
        preferentialTax:false,
        test:false
    },
//     computed:{
//         preferentialTax(){
//             return this.$refs.preferentialTax.checked;
//     }
// },
    methods: {
        putForm(){
            // console.log(123);
            // console.log(this.checkNames);
            console.log(this.preferentialTax===false);
            // console.log(this.test);
        },
        store() {
            sessionStorage.setItem('dataTest', this.dataTest);
            this.num = this.dataTest;
            console.log(this.num);
        },
        
        click() {

            axios({
                method: 'post',
                url: "/api/user/signIn",
                data: {
                    "userId": "2000301103"
                }

            }).then(res => {
                console.log(res);
                this.msg = res;
            }).catch(err => {
                console.log(err);
            })
        },
        clickData(){
            this.msgData=this.dataTest;
            console.log(this.num);
        }
    },
})