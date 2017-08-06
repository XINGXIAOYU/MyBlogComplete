/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function goodplus(id) {
    var likeornot = $(".footer :nth-child(3)").prop('class');
    var num = parseInt($(".num").html());
    if (likeornot=="like") {
        num = num + 1;
        $(".like a").html("取消赞");
        $(".like").prop('class', "unlike");        
        $.post("like.jsp", {essay_id: id,num:num}, function (text) {
            $(".num").html(text);
        });
    } else if(likeornot=="unlike") {
        num = num -1;
        $(".unlike a").html("赞");
        $(".unlike").prop('class', "like"); 
        $.post("like.jsp", {essay_id: id,num:num}, function (text) {
            $(".num").html(text);
        });
    }
}

