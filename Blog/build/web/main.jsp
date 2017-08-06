<%-- 
    Document   : index
    Created on : 2015-6-8, 8:24:02
    Author     : xingxiaoyu
--%>

<%@page import="operation.EssayAct"%>
<%@page import="information.EssayClass"%>
<%@page import="information.Essay"%>
<%@page import="information.User"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <!----webfonts---->
        <link href='http://fonts.useso.com/css?family=Oswald:100,400,300,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.useso.com/css?family=Source+Sans+Pro:200,300,400,600,700,300italic,400italic,600italic' rel='stylesheet' type='text/css'>
        <!----//webfonts---->

        <link rel="stylesheet" type="text/css"
              href="css/style4.css"/>
        <script src="js/jquery.js"></script>
        <script src="js/blog.js"></script>
        <script src="js/dianzan.js"></script>
    </head>
    <body class="index">
        <%if ((User) session.getAttribute("user") != null || session.getAttribute("idName") != null) {%>
        <div class="wrapper">
            <div id="to-top"></div>
            <div class="container">
                <div class="menu"> 
                    <!--the nav of the blog-->
                    <ul>
                        <li id="blog_main" class="active"><a href="main.jsp">首页</a> </li>
                        <li id="blog_list" ><a href="blog_list.jsp">博文目录</a> </li>
                        <li id="image" ><a href="">图片</a> </li>
                        <li id="aboutMe"><a href="aboutMe.jsp">关于我</a></li>
                    </ul>
                </div>
            </div>

            <div class="head">
                <%User user = (User) session.getAttribute("user");%>
                <h1><%=session.getAttribute("name")%>的博客</h1>
                <h2>http://blog.<%=session.getAttribute("idName")%>.com</h2>
            </div>

            <div class="content">
                <div class="main_content">
                    <div class="title">博文</div>
                    <%ArrayList<Essay> essay = (ArrayList<Essay>) session.getAttribute("essayList");
                        EssayAct act = new EssayAct();%>
                    <%for (int i = 0; i < essay.size(); i++) {%>
                    <%="<div class='article'>"%>
                    <%="<div class='article_content'>"%>
                    <%="<div class='article_content_head'>"%>
                    <%="<h3 class='articleName'>"%>
                    <%="<a href ='blog.jsp?essay_id="%><%=essay.get(i).getEssay_id()%><%="'>" + essay.get(i).getEname() + "</a>"%>
                    <%="</h3>"%>
                    <%="<h4 class='time'>" + essay.get(i).getPbtime() + ",Posted by " + essay.get(i).getAuthorName() + "</h4>"%>
                    <%if (essay.get(i).getTags().size() != 0) {%>
                    <%="<h4 class='label'>标签:"%>
                    <%for (int j = 0; j < essay.get(i).getTags().size(); j++) {%>
                    <%=essay.get(i).getTags().get(j)%>
                    <%}%>
                    <%="</h4>"%>
                    <%}%>
                    <%="</div>"%>
                    <%="<div class='article_content_info'>"%>
                    <%if (essay.get(i).getContent().length() >= 200) {%>
                    <%="<p>" + essay.get(i).getContent().substring(0, 200) + "</p>"%>
                    <%} else {%>
                    <%="<p>" + essay.get(i).getContent() + "</p>"%>
                    <%}%>
                    <%="</div>"%>
                    <%="<div class='footer'>"%>
                    <%="<span class='readAll'>"%>
                    <%="<a href ='blog.jsp?essay_id=" + essay.get(i).getEssay_id() + "'>"%>阅读全文<%="</a>"%>
                    <%="</span>"%>
                    <%="<span class='read'>"%>阅读量：<%="</span>"%>
                    <%="<span class='rnum'>"%><%=act.getEssayRead(essay.get(i).getEssay_id())%><%="</span>"%>
                    <%="</div>"%>
                    <%="</div>"%>
                    <%="</div>"%>
                    <%}%>
                </div>
                <div class="main_content_right">
                    <div class="information"> 
                        <div class="title">个人资料</div>
                        <div> <img id = "photo" src="<%=session.getAttribute("photo")%>" alt="picture is not available"/>
                            <div id="bloggerName"><%=session.getAttribute("name")%></div>
                        </div>
                        <div id="introduction"><%=session.getAttribute("introduction")%></div>
                    </div>
                    <div class="categories"> 
                        <!--classification of the articles-->
                        <div class="title" onclick="">分类</div>
                        <%ArrayList<EssayClass> essayClass = (ArrayList<EssayClass>) session.getAttribute("essayClassList");%>
                        <%for (int i = 0; i < essayClass.size(); i++) {
                        %>
                        <%="<li>" + essayClass.get(i).getClassName() + "(" + essayClass.get(i).getEssayNum() + ")" + "</li>"%>
                        <%}%>  

                    </div>
                </div>

                <!--foot-->
                <div class="copywrite">
                    <p>Copyright &copy; 2015 XXX,All Rights ReservedXXX</p>
                </div>
            </div>
        </div>
        <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"></span></a>
            <%} else {
                response.sendRedirect("index.jsp");
            }%>
    </body>
</html>
