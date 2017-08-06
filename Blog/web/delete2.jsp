<%-- 
    Document   : delete2
    Created on : 2015-6-21, 14:03:16
    Author     : xingxiaoyu
--%>

<%@page import="operation.EssayAct"%>
<%@page import="operation.UserAct"%>
<%@page import="information.User"%>
<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%
    User user = (User) session.getAttribute("user");
    Connection conn = ConnectDB.getConnection();
    int class_id = Integer.parseInt(request.getParameter("class_id"));
    String className = "";
    for (int i = 0; i < user.getEssayClass().size(); i++) {
        if (class_id == user.getEssayClass().get(i).getClass_id()) {
            className = user.getEssayClass().get(i).getClassName();
        }
    }
    String sql = "delete from class where `class_id` = " + class_id + ";";
    try {
        Statement stm = (Statement) conn.createStatement();
        stm.executeUpdate(sql);
        stm.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    String sql2 = "update essay set `classname`='" + "未分类博文" + "' where `classname`='" + className + "' and `user_id`='" + user.getId() + "';";
    try {
        Statement stm = (Statement) conn.createStatement();
        stm.executeUpdate(sql2);
        stm.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    UserAct act = new UserAct();
    act.update(user, request);
    if (conn.isClosed() == false) {
        ConnectDB.closeConnection(conn);
    }
%>
