<!DOCTYPE HTML>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Home</title>
        <!-- Custom Theme files -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <!-- Custom Theme files -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!--Google Fonts-->
        <link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>
        <link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <!--Google Fonts-->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <script src="js/jquery.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="wrapper">
            <fieldset class="login">
                <div class="login-top">
                    <h1>LOGIN FORM</h1>
                    <form action="LoginServlet" id="myForm1" method="post">
                        <input type="text" value="" name="idName" id="idName" placeholder="user id">
                        <input type="password" value="" name="password" id="password" placeholder="password">
                    </form>
                    <div class="deal">
                        <input type="submit" value="Login" id="login">
                    </div>
                </div>
                <div class="login-bottom">
                    <h3>New User &nbsp;<a id="register">Register</a>&nbsp Here</h3>
                </div>
            </fieldset>
            <fieldset class="register">
                <div class="register-top">
                    <h1>REGISTER FORM</h1>
                    <form action="RegServlet" id="myForm2" method="post">
                        <input type="text" value="" name="regidName" id="regidName" placeholder="user id"><span id="a"></span>
                        <input type="text" value="" name="name" id="name" placeholder="nickname"><span id="b"></span>
                        <input type="password" value="" name="regpassword" id="regpassword" placeholder="password"><span id="c"></span>
                        <input type="password" value="" name="rpassword" id="rpassword" placeholder="validate password"><span id="d"></span>
                    </form>
                    <div class="deal">
                        <input type="submit" value="DONE" name="ok" id="ok">
                    </div>
                </div>
                <div class="register-bottom">
                    <h3>I wanna go &nbsp;<a id="back">BACK</a>&nbsp</h3>
                </div>
            </fieldset>
            <fieldset class="popular">
                <div class="popular-top">
                    <h1>RECOMMENDATION</h1>
                    <%="<ul>"%>
                    <%
                        Connection conn = (Connection) ConnectDB.getConnection();
                        String sql = "select * from essay";
                        int count = 0;
                        try {
                            Statement stmt = (Statement) conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                count++;
                                if (count <= 5) {%>

                    <%="<li>"%>
                    <%="<a href='"%><%="LoginServlet?essay_id=" + rs.getInt("essay_id") + "'>"%><%=rs.getString("essay_name") + "</a>"%>
                    <%if (rs.getString("content").length() >= 200) {%>
                    <%="<div>" + rs.getString("content").substring(0, 200) + "</div>"%>
                    <%} else {%>
                    <%="<div>" + rs.getString("content") + "</div>"%>
                    <%}%>
                    <%="</li>"%>

                    <%} else {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }%>
                    <%="</ul>"%>
                </div>
            </fieldset>
        </div>
        <script src="js/checkindex.js" type="text/javascript" charset="utf-8" ></script>
    </body>

</html>