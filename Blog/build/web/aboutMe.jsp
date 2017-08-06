<%-- 
    Document   : aboutMe
    Created on : 2015-6-25, 19:59:41
    Author     : xingxiaoyu
--%>

<%@page import="information.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" 
              href="css/style4.css"/>
        <script src="js/jquery.js"></script>
        <script src="js/checkInfo.js"></script>  
    </head>
    <body class="aboutMe">
        <%
            User user = (User) session.getAttribute("user");
            if (user != null && user.getId() == (int) session.getAttribute("user_id")) {
        %>
        <div class="wrapper">
            <div class="container">
                <div class="menu"> 
                    <!--the nav of the blog-->
                    <ul>
                        <li id="blog_main"><a href="main.jsp">首页</a> </li>
                        <li id="blog_list" ><a href="blog_list.jsp">博文目录</a> </li>
                        <li id="image" ><a href="">图片</a> </li>
                        <li id="aboutMe"><a href="">关于我</a></li>
                    </ul>
                </div>
            </div>
            <div class="head">
                <h1><%=session.getAttribute("name")%>的博客</h1>
                <h2>http://blog.<%=session.getAttribute("idName")%>.com</h2>
            </div>
            <div class="content">
                <h3>个人资料</h3>
                <form id="myForm2" action="UploadServlet" method="post" enctype="multipart/form-data">
                    <div id="hh">
                        <div id="preview">
                            <img  id="imghead" src="<%=session.getAttribute("photo")%>" alt=""/>
                        </div>
                        <input type="file" name="myfile" onchange="previewImage(this)"/> 
                    </div>
                    <div class="article_content_info">
                        <h4 class = "mtitle">昵称</h4>
                        <input type = "text" name = "cute" id="cute" value=""/>
                        <h4 class = "mtitle">密码</h4>
                        <input type = "password" name = "pw" id="pw" value=""/>
                        <h4 class = "mtitle">确认密码</h4>
                        <input type = "password" name = "rpw" id="rpw" value=""/>

                        <div class="clear"></div>
                    </div>                      
                    <div id="ok">
                        <span>
                            <%="<a onclick='checkAll(\""%><%=user.getPassword()%><%="\")'>确认修改</a>"%>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <%
            } else {
                response.sendRedirect("index.jsp");
            }
        %>

    </body>
</html>
