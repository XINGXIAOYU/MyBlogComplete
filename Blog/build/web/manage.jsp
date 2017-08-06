<%-- 
    Document   : manage
    Created on : 2015-6-16, 21:47:39
    Author     : xingxiaoyu
--%>

<%@page import="information.EssayClass"%>
<%@page import="java.util.*"%>
<%@page import="information.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>manage class</title>
        <link rel="stylesheet" type="text/css"
              href="css/style4.css"/>
        <script src="js/jquery.js"></script>

    </head>
    <body id="manage">
        <%if((User) session.getAttribute("user")!=null){%>
        <div class="wrapper">
            <div id="to-top"></div>
            <div class="container">
                <div class="menu"> 
                    <!--the nav of the blog-->
                    <ul>
                        <li id="blog_main" ><a href="main.jsp">首页</a> </li>
                        <li id="blog_list" class="active"><a href="blog_list.jsp">博文目录</a> </li>
                        <li id="image" ><a href="">图片</a> </li>
                        <li id="aboutMe"><a href="">关于我</a></li>
                    </ul>
                </div>
            </div>

            <div class="head">
                <%User user = (User) session.getAttribute("user");%>
                <h1><%=session.getAttribute("name")%>的博客</h1>
                <h2>http://blog.<%=session.getAttribute("idName")%>.com</h2>
            </div>
            <%ArrayList<EssayClass> essayClass = user.getEssayClass();%>
            <div class="content">
                <div class="title">分类管理</div>
                <%="<table id ='tb'>"%>
                <%for (int i = 0; i < user.getEssayClass().size(); i++) {%>
                <%="<tr id='"%><%=essayClass.get(i).getClass_id()%><%="'>"%>
                <%="<td class ='className'>" + essayClass.get(i).getClassName() + "</td>"%>
                <%="<td>" + "(" + essayClass.get(i).getEssayNum() + ")" + "</td>"%>
                <%="<td><input type='text' value='' onblur='sendData("%><%=essayClass.get(i).getClass_id()%><%=")'/></td>"%>
                <%="<td id='you'>"%>
                <%if (essayClass.get(i).getClassName().equals("未分类博文") == false) {%>
                <%="<span><a class='rename' onclick='rename("%><%=essayClass.get(i).getClass_id()%><%=")'>重命名</a></span>"%>
                <%="<span><a class='delete' onclick ='ECdelete(" + essayClass.get(i).getClass_id() + ")'>删除</a></span></td>"%>
                <%}%>
                <%="</tr>"%>
                <%}%>
                <%="</table>"%>
            </div>
            <div class="copywrite">
                <p>Copyright &copy; 2015 XXX,All Rights ReservedXXX</p>
            </div>
        </div>
        <script src="js/manage.js"></script>
        <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"></span></a>
        <%}else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }%>
    </body>
</html>
