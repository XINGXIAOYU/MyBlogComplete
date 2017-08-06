<%-- 
    Document   : delete
    Created on : 2015-6-21, 9:40:47
    Author     : xingxiaoyu
--%>

<%@page import="operation.EssayAct"%>
<%@page import="operation.UserAct"%>
<%@page import="information.User"%>
<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>


<%
    User user = (User) session.getAttribute("user");
    Connection conn = ConnectDB.getConnection();
    int essay_id = Integer.parseInt(request.getParameter("essay_id"));
    String sql = "delete from essay where `essay_id` = " + essay_id + ";";
    try {
        Statement stm = (Statement) conn.createStatement();
        stm.executeUpdate(sql);
        stm.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    String sql2 = "delete from comment where `essay_id` = " + essay_id + ";";
    try {
        Statement stm = (Statement) conn.createStatement();
        stm.executeUpdate(sql2);
        stm.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    String sql3 = "delete from trans where `essay_id` = " + essay_id + ";";
    try {
        Statement stm = (Statement) conn.createStatement();
        stm.executeUpdate(sql3);
        stm.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    UserAct act = new UserAct();
    act.update(user, request);
    if (conn.isClosed() == false) {
        ConnectDB.closeConnection(conn);
    }
    PrintWriter p = response.getWriter();
    response.setContentType("html/text");
    int num = user.getEssay().size();
    p.println(num);
%>
