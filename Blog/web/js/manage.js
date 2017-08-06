/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("input[type]").hide();
function rename(id) {
    var id = id;
    $("#" + id + " td input[type]").show();
}
function sendData(id) {
    var id = id;
    var className = $("#" + id + " td input[type]").prop('value');
    var oldClassName = $("#" + id + " td.className").text();
    if (className != "") {
        $.post("rename.jsp", {class_id: id, className: className, oldClassName: oldClassName}, function (text) {
            $("#" + id + " td.className").html(text);
        });
    }
}
function ECdelete(id) {
    var g = confirm("确定要删除?");
    if (g == true) {
        var id = id;
        $("tr#" + id).remove();
        $.post("delete2.jsp", {class_id: id});
    }
}


