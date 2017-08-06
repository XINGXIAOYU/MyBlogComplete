<%-- 
    Document   : readEssayOfClass
    Created on : 2015-6-21, 14:33:37
    Author     : xingxiaoyu
--%>

<%@page import="information.Essay"%>
<%@page import="information.EssayClass"%>
<%@page import="operation.UserAct"%>
<%@page import="information.User"%>
<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Data</title>
    </head>
    <body>
        <%
            Connection conn = ConnectDB.getConnection();
            int class_id = Integer.parseInt(request.getParameter("class_id"));
            ArrayList<EssayClass> array = (ArrayList<EssayClass>) session.getAttribute("essayClassList");
            ArrayList<Essay> array2 = (ArrayList<Essay>)session.getAttribute("essayList");
            ArrayList<Essay> array3 = new ArrayList<Essay>();
            String className = "";
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getClass_id() == class_id) {
                    className = array.get(i).getClassName();                  
                }
            }
            for(int i = 0;i<array2.size();i++){
                if(array2.get(i).getClassName().equals(className)){
                    array3.add(array2.get(i));
                }
            }
        %>
        <div class="title"><%=className%>（<span id="num"><%=array3.size()%></span>）</div>
        <%for (int i = 0; i < array3.size(); i++) {
        %>
        <%="<div class='article' id='"%><%=array3.get(i).getEssay_id() + "'>"%>
        <%="<div class='article_content'>"%>
        <%="<div class='article_content_head'>"%>
        <%="<h3 class='articleName'>" + "<a href ='blog.jsp?essay_id=" + array3.get(i).getEssay_id() + "'>" + array3.get(i).getEname() + "</a></h3>"%>
        <%="<h4 class='time'>" + array2.get(i).getPbtime() + ",Posted by " + array3.get(i).getAuthorName() + "</h4>"%>
        <%if (array3.get(i).getTags().size() != 0) {%>
        <%="<h4 class='label'>标签:"%>
        <%for (int j = 0; j < array3.get(i).getTags().size(); j++) {%>
        <%=array3.get(i).getTags().get(j)%>
        <%}%>
        <%="</h4>"%>
        <%}%>
        <%="</div>"%>
        <%="<div class='footer'>"%>
        <%if (session.getAttribute("user") != null) {%>
        <%="<span class = 'edit'>"%>
        <%="<a>编辑"%><%="</a>"%>
        <%="</span>"%>
        <%="<span class = 'delete'>"%>
        <%="<a onclick='deleteE("%><%=array3.get(i).getEssay_id()%><%=")'>"%>删除<%="</a>"%>
        <%="</span>"%>
        <%}%>               
        <%="</div>"%>
        <%="</div>"%>
        <%="</div>"%>
        <%}%>
</body>
</html>
