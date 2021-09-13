var mybutton = document.getElementsByClassName("mybutton")[0]
var myinput = document.getElementsByClassName("myinput")
var mycheck = document.getElementsByClassName("form-check-input")
var people = document.getElementsByClassName("form-control")
mybutton.addEventListener("click", function() {
    for (var i = 0; i < myinput.length; i++) { //输入框的值
        console.log(myinput[i].value);
    }
    for (var i = 0; i < mycheck.length; i++) { //拿到check的值
        if (mycheck[i].checked === true) {
            console.log(mycheck[i].parentElement.childNodes[3].childNodes[0].data.trim());
        }
    }
    for (var i = 0; i < people.length; i++) {
        console.log(people[i].value);
    }
});
