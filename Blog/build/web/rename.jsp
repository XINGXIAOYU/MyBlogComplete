<%-- 
    Document   : rename
    Created on : 2015-6-16, 22:47:30
    Author     : xingxiaoyu
--%>

<%@page import="operation.UserAct"%>
<%@page import="information.User"%>
<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>rename class</title>
    </head>
    <body>
        <%
            Connection conn = ConnectDB.getConnection();
            int class_id = Integer.parseInt(request.getParameter("class_id"));
            String className = request.getParameter("className");
            String oldClassName = request.getParameter("oldClassName");
            String sql = "update class set `className` = '" + className + "' where `class_id` = " + class_id + ";";
            try {
                Statement stm = (Statement) conn.createStatement();
                stm.executeUpdate(sql);
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter p = response.getWriter();
            response.setContentType("html/text");
            p.println(className);

            User user = (User) session.getAttribute("user");
            for (int i = 0; i < user.getEssay().size(); i++) {
                if (user.getEssay().get(i).getClassName().equals(oldClassName)) {
                    String sql2 = "update essay set `className` = '" + className + "' where `essay_id` = " + user.getEssay().get(i).getEssay_id() + ";";
                    try {
                        Statement stm = (Statement) conn.createStatement();
                        stm.executeUpdate(sql2);
                        stm.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        ConnectDB.closeConnection(conn);
                    }
                }
            }
            UserAct act = new UserAct();
            act.update(user,request);
            if (conn.isClosed() == false) {
                ConnectDB.closeConnection(conn);
            }
        %>
    </body>
</html>
