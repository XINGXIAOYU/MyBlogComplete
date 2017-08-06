<%-- 
    Document   : like
    Created on : 2015-6-15, 23:51:15
    Author     : xingxiaoyu
--%>

<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Connection conn = ConnectDB.getConnection();
            int essay_id = Integer.parseInt(request.getParameter("essay_id"));
            int num = Integer.parseInt(request.getParameter("num"));
            String sql = "update essay set `like` = " + num + " where `essay_id` = " + essay_id + ";";
            try {
                Statement stm = (Statement) conn.createStatement();
                stm.executeUpdate(sql);
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectDB.closeConnection(conn);
            }

            PrintWriter p = response.getWriter();
            response.setContentType("html/text");
            p.println(num);

        %>  
    </body>
</html>
