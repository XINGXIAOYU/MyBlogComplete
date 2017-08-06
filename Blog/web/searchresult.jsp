<%-- 
    Document   : searchresult
    Created on : 2015-6-21, 23:40:05
    Author     : xingxiaoyu
--%>

<%@page import="information.EssayClass"%>
<%@page import="information.User"%>
<%@page import="information.Essay"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
        <link rel="stylesheet" type="text/css"
              href="css/style4.css"/>
        <script src="js/jquery.js"></script>
    </head>
    <body>
        <%if ((User) session.getAttribute("user") != null || session.getAttribute("idName") != null) {%>
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
            <div class="content" id="result">
                <div class="main_content">
                    <div class="title">搜索结果</div>
                    <%ArrayList<Essay> essay = (ArrayList<Essay>) session.getAttribute("result");
                        if (essay.size() == 0) {
                    %>
                    <h3>没有符合搜索条件的博文</h3>
                    <%} else {%>
                    <span><%="<a href='SearchServlet?sort=1'>"%>按时间升序<%="</a>"%></span>
                    <span><%="<a href='SearchServlet?sort=2'>"%>按时间降序<%="</a>"%></span>
                    <%for (int i = 0; i < essay.size(); i++) {
                    %>
                    <%="<div class='article' id='"%><%=essay.get(i).getEssay_id() + "'>"%>
                    <%="<div class='article_content'>"%>
                    <%="<div class='article_content_head'>"%>
                    <%="<h3 class='articleName'>" + "<a href ='blog.jsp?essay_id=" + essay.get(i).getEssay_id() + "'>" + essay.get(i).getEname() + "</a></h3>"%>
                    <%="<h4 class='time'>" + essay.get(i).getPbtime() + ",Posted by " + essay.get(i).getAuthorName() + "</h4>"%>
                    <%if (essay.get(i).getTags().size() != 0) {%>
                    <%="<h4 class='label'>标签:"%>
                    <%for (int j = 0; j < essay.get(i).getTags().size(); j++) {%>
                    <%=essay.get(i).getTags().get(j)%>
                    <%}%>
                    <%="</h4>"%>
                    <%}%>
                    <%="</div>"%>
                    <%="</div>"%>
                    <%="</div>"%>
                    <%}%>
                    <%}%>
                </div>
                <div class="main_content_right">
                    <div class="search">
                        <h3>SEARCH HERE</h3>
                        <form action="SearchServlet" method="post">
                            <input type="text" value="" name="condition"/>
                            <div>
                                <input type="radio" checked="checked" name="range" value="1"/> 当前用户
                                <input type="radio" name="range" value="2"/> 全站搜索
                            </div>
                            <div>
                                <input type="radio" checked="checked" name="keyword" value="1"/> 标题
                                <input type="radio" name="keyword" value="2"/> 正文
                                <input type="radio" name="keyword" value="3"/> 标签
                            </div>
                            <input type="submit" value="SEARCH">
                        </form>
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
