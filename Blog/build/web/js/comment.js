/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function comment(essay_id, user_id) {
    var essay_id = essay_id;
    var content = $("#textarea").val();
    var user_id = user_id;
    if (content == "") {
        alert("评论不能为空！");
    } else {
        $.post("comment.jsp", {essay_id: essay_id, user_id: user_id, content: content}, function (text) {
            $(".comment-grid").html(text);
        });
    }
}

function deleteC(comment_id, essay_id) {
    var comment_id = comment_id;
    var essay_id = essay_id;
    $.post("deleteComment.jsp", {comment_id: comment_id, essay_id: essay_id}, function (text) {
        $(".comment-grid").html(text);
    });
}


