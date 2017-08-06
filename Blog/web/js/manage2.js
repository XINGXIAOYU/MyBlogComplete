/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function deleteE(essay_id, classid) {
    var g = confirm("确定要删除?");
    if (g == true) {
        var essay_id = essay_id;
        var classid = classid;
        $("div#" + essay_id).remove();
        $.post("delete.jsp", {essay_id: essay_id}, function (text) {
            $(".cnum").html(text);
        });
        $.post("delete3.jsp", {classid: classid}, function (text) {
            $("#" + classid).html(text);
        });
    }
}

function readEssays(class_id) {
    var class_id = class_id;
    $.post("readEssayOfClass.jsp", {class_id: class_id}, function (text) {
        $("#main").html(text);
    });

}

function readAll() {
    location.reload();
}





