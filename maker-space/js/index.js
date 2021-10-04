var mybutton = document.getElementsByClassName("mybutton")[0]
var myinput = document.getElementsByClassName("myinput")
var mycheck = document.getElementsByClassName("form-check-input")
var people = document.getElementsByClassName("form-control")

var mywarn = document.getElementsByClassName("mywarn")[0]

// mybutton.addEventListener("click", )
function daochu() {
    var input=false,check=false,peoplet=false;
    if(myinput===undefined){
        input=true;
    }
    if(mycheck===undefined){
        check=true
    }
    if(people===undefined){
        peoplet=true
    }
    for (var i = 0; i < myinput.length; i++) { //输入框的值
        if(myinput[i].value===''){
            mywarn.style.visibility='visible'
        console.log(1);
        }else{
            // console.log(myinput[i].value);
            input=true;
        }
    }
    for (var i = 0; i < mycheck.length; i++) { //拿到check的值
        if(myinput[i]===''){
            mywarn.style.visibility='visible'
        console.log(1);
    }
        else {
            check=true;
        }
    }
    for (var i = 0; i < people.length; i++) {
        console.log(1);
        if(people[i].value===''){
            mywarn.style.visibility='visible'
        }else{
            // console.log(people[i].value);
            peoplet=true
        }
    }
    if(check&&input&&peoplet){
            mywarn.style.visibility='hidden'

    }
};