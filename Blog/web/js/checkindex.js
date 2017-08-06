/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//logincheck
function check() {
    var idName = document.getElementById("idName").value;
    var password = document.getElementById("password").value;
    if (idName === "" || password === "") {
        alert("用户名或密码不能为空");
    } else {
        var form = document.getElementById("myForm1");
        form.submit();
    }
}

//check register
function checkID() {
    var regidName = document.getElementById("regidName").value;
    var result = document.getElementById("a");
    var rule = /[a-zA-Z0-9_]{2,14}|^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    if (regidName.trim().length === 0) {
        result.innerHTML = "账号不能为空";
        valid = false;
    } else if ((!regidName.match(rule)) && regidName != "") {
        result.innerHTML = "英文或数字或下划线,长度2-14字符或正确邮箱";
        valid = false;
    }
    else {
        result.innerHTML = "";
        valid = true;
    }
}
function checkName() {
    var name = document.getElementById("name").value;

    var result = document.getElementById("b");
    if (name.trim().length === 0) {
        result.innerHTML = "昵称不能为空";
        valid = false;
    } else {
        result.innerHTML = "";
        valid = true;
    }
}
function checkPassword() {
    var password = document.getElementById("regpassword").value;
    var result = document.getElementById("c");
    var rule1 = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,}$/;
    var rule2 = /[^\d]/ig;
    if (password.trim().length === 0) {
        result.innerHTML = "密码不能为空";
        valid = false;

    } else if (!password.match(rule1) && !password.match(rule2) && password !== ""||password.length<6) {
        result.innerHTML = "英文、数字或符号,长度必须大于6位,且不得是纯数字";
        valid = false;

    } else {
        result.innerHTML = "";
        valid = true;
    }
}
function checkRPassword() {
    var password = document.getElementById("regpassword").value;

    var rpassword = document.getElementById("rpassword").value;

    var result = document.getElementById("d");
    if (rpassword.trim().length === 0) {
        result.innerHTML = "确认密码不能为空";
        valid = false;
    }
    else if (password !== rpassword) {
        result.innerHTML = "两次输入密码不一致";
        valid = false;
    } else {
        result.innerHTML = "";
        valid = true;
    }
}
function checkAll() {
    checkID();
    checkName();
    checkPassword();
    checkRPassword();
    if (valid == true) {
        var form = document.getElementById("myForm2");
        form.submit();
    }
}

$("#regidName").blur(checkID);
$("#name").blur(checkName);
$("#regpassword").blur(checkPassword);
$("#rpassword").blur(checkRPassword);
$("#ok").click(checkAll);
$("#login").click(check);

//transfer page
var log;
var reg;
var regbutn = $("#register");
regbutn.click(register);
var back = $("#back");
back.click(login);
function register() {
    log = $(this).parent().parent().parent();
    reg = $(this).parent().parent().parent().next();
    reg.show();
    log.animate({opacity: 0}, {
        step: function (now, mx) {
            scale = 1 - (1 - now) * 0.2;
            opacity = 1 - now;
            log.css({'transform': 'scale(' + scale + ')'});
            reg.css({'opacity': opacity});
        },
        duration: 800,
        complete: function () {
            log.hide();
            log.css({'transform': 'scale(1)'});
            animating = false;
        }
    });
}

function login() {
    reg = $(this).parent().parent().parent();
    log = $(this).parent().parent().parent().prev();
    log.show();
    reg.animate({opacity: 0}, {
        step: function (now, mx) {
            scale = 1 - (1 - now) * 0.2;
            opacity = 1 - now;
            reg.css({'transform': 'scale(' + scale + ')'});
            log.css({'opacity': opacity});
        },
        duration: 800,
        complete: function () {
            reg.hide();
            reg.css({'transform': 'scale(1)'});
            animating = false;
        }
    });
}







