/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function check() {
    var biaoti = $.trim($("#biaoti").val());
    var content = $.trim($("#textarea").val());
    var text = $.trim($("#class").val());
    var option = $("#fenlei option");
    var ok = true;
    if (biaoti.trim() === "") {
        alert("标题不能为空!");
        ok = false;
    }
    if (content.trim() === "") {
        alert("文章内容不能为空!");
        ok = false;
    }
    for (var i = 0; i < option.length; i++) {
        if (option.get(i).text == (text)) {
            alert("分类已经存在");
            ok = false;
        }
    }
    var label = $("#biaoqian").val();
    var str = new String();
    str = label;
    var arr = new Array();
    arr = str.split(' ');
    for (var i = 0; i < arr.length; i++) {
        if (i != arr.length - 1 && arr[i] == arr[i + 1]) {
            alert("输入标签重复,请重新输入！");
            ok = false;
        }
    }
    if (ok == true) {
        alert("发表成功！");
        var form = document.getElementById("myForm");
        form.submit();
    }
}


$("#fabowen").click(check);
$("#class").hide();
$("#createClass").click(function () {
    $("#class").show();
});
