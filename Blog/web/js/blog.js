/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function showBlog(essay_id){
    var essay_id = essay_id;
    $.post("blog.jsp", {essay_id: essay_id}, function (text) {
            $(".main_content").replaceWith(text);
        });
}


