<%-- 
    Document   : message
    Created on : 2015-5-23, 14:56:27
    Author     : xingxiaoyu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="information.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> 
            <%
                // 获取提示信息
                String info = (String) request.getAttribute("info");
                // 如果提示信息不为空，则输出提示信息
                if (info != null) {%>
                <span><%=info%></span>
            <%}
                // 获取登录的用户信息
                User user = (User) session.getAttribute("user");
                    // 判断用户是否登录
                    if (user != null) {%>
            <script type="text/javascript">
                window.setTimeout("window.location='main.jsp'", 2000);
            </script>
            <%
            } else {
            %>
            <script type="text/javascript">
                window.setTimeout("window.location='index.jsp'", 2000);
            </script>
            <%
                }
            %>
        </h1>
    </body>
</html>
