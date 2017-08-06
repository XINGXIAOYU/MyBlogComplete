/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sort(key,essays) {
    alert("");
    $.post("resultsort.jsp", {key:key,essays:essays}, function (text) {
        $(".article_content_head").html(text);
    });
}


