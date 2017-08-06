<%-- 
    Document   : delete3
    Created on : 2015-6-21, 21:25:48
    Author     : xingxiaoyu
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="information.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User) session.getAttribute("user");
    int classid = (int) request.getAttribute("classid");
    for (int i = 0; i < user.getEssayClass().size(); i++) {
        if (classid == user.getEssayClass().get(i).getClass_id()) {
            PrintWriter p = response.getWriter();
            response.setContentType("html/text");
            int num = user.getEssayClass().get(i).getEssayNum();
            p.println(num);
        }
    }
%>

