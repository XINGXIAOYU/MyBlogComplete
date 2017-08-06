<%-- 
    Document   : blog
    Created on : 2015-6-20, 12:44:41
    Author     : xingxiaoyu
--%>

<%@page import="operation.EssayAct"%>
<%@page import="information.EssayClass"%>
<%@page import="information.Comment"%>
<%@page import="information.Essay"%>
<%@page import="operation.UserAct"%>
<%@page import="information.User"%>
<%@page import="operation.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>
        <link rel="stylesheet" type="text/css" 
              href="css/style4.css"/>
        <script src="js/jquery.js"></script>
        <script src="js/dianzan.js"></script>
        <script src="js/comment.js"></script>
    </head>
    <body class="blog">
        <%  User user = (User) session.getAttribute("user");
            if (user != null || request.getParameter("essay_id") != null) {%>
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
                <%
                    int essay_id = Integer.parseInt(request.getParameter("essay_id"));
                    EssayAct act = new EssayAct();
                    User author = new User();
                    author = act.getAuthor(essay_id);
                %>
                <h1><%=author.getName()%>的博客</h1>
                <h2>http://blog.<%=author.getIdName()%>.com</h2>
            </div>
            <%
                ArrayList<Essay> list = (ArrayList<Essay>) author.getEssay();

                for (int i = 0; i < list.size(); i++) {
                    if (essay_id == list.get(i).getEssay_id()) {
                        Integer read = list.get(i).getRead();
                        if (application.getAttribute("read" + essay_id) == null) {
                            application.setAttribute("read" + essay_id, read);
                        }
                        if (user == null || user.getIdName().equals(author.getIdName()) == false) {
                            read++;
                            application.setAttribute("read" + essay_id, read);//累加后再放进去
                        }
                        if ((Integer) application.getAttribute("read" + essay_id) != list.get(i).getRead()) {
                            act.savereading(essay_id, read);
                        }
                        list.get(i).setRead(read);
            %>
            <div class="content">
                <div class="main_content">
                    <div class="title">博文</div>
                    <div class="article">
                        <div class="article_content">
                            <div class="article_content_head">
                                <h3 class="articleName"><%=list.get(i).getEname()%></h3>
                                <h4 class="time"><%=list.get(i).getPbtime() + ",Posted by " + list.get(i).getAuthorName()%></h4>
                                <%if (list.get(i).getTags().size() != 0) {%>
                                <h4 class='label'>标签:
                                    <%for (int j = 0; j < list.get(i).getTags().size(); j++) {%>
                                    <%=list.get(i).getTags().get(j)%>
                                    <%}%>
                                    分类:
                                    <%=list.get(i).getClassName()%>
                                </h4>

                                <%}%>
                            </div>
                            <div class="article_content_info">
                                <p><%=list.get(i).getContent()%></p>
                            </div>
                            <div class="footer">
                                <span class="read">阅读量：</span>
                                <span class="rnum"><%=list.get(i).getRead()%></span>
                                <%if (session.getAttribute("user") != null) {%>
                                <span class="like"><%="<a onclick='goodplus("%><%=list.get(i).getEssay_id() + ")'>"%>赞<%="</a></span>"%>
                                    <%} else {%>
                                    <span id="no"><a>赞</a></span>
                                    <%}%>
                                    <span class="num"><%=list.get(i).getLike()%></span>
                            </div>
                        </div>
                        <%if (user != null) {%>
                        <div class="content-form">
                            <h3 class="mtitle" id="pinglun">Leave A Comment</h3>
                            <form>
                                <textarea id="textarea" placeholder="Message"></textarea>
                                <%="<input type='button' value='SEND' onclick ='comment("%><%=essay_id + "," + user.getId()%><%=")'>"%>
                            </form>
                        </div>
                        <%}%>

                        <div class="comments">
                            <h3>Comment</h3>
                            <div class="comment-grid">
                                <%
                                    ArrayList<Comment> comment = list.get(i).getComment();
                                %>
                                <%if (comment.size() != 0) {%>
                                <%for (int m = 0; m < comment.size(); m++) {%>
                                <div class="comment-info">
                                    <img src="<%=comment.get(m).getAuthor(comment.get(m).getUser_id()).getPhoto()%>" alt=""/>
                                    <h4><%=comment.get(m).getAuthor(comment.get(m).getUser_id()).getName()%></h4>
                                    <p><%=comment.get(m).getContent()%></p>
                                    <h5><%=comment.get(m).getTime()%></h5>
                                    <%if ((user != null && comment.get(m).getAuthor(comment.get(m).getUser_id()).getIdName().equals(user.getIdName()))||(user!=null&&user.getId()==author.getId())) {%>
                                    <span class='delete'><%="<a onclick ='deleteC("%><%=comment.get(m).getComment_id() + "," + essay_id%><%=")'>"%>删除<%="</a>"%></span>
                                    <%}%>
                                </div>
                                <div class="clear"></div>
                                <%}

                                    }%> 
                            </div>
                        </div>
                    </div>
                    <div class="more">
                        <%if (i != 0) {%>
                        <%="<span id='b'><a href='blog.jsp?essay_id="%><%=list.get(i - 1).getEssay_id()%><%="'>前一篇</a></span>"%>
                        <%}%>
                        <%if (i != list.size() - 1) {%>
                        <%="<span id='a'><a href='blog.jsp?essay_id="%><%=list.get(i + 1).getEssay_id()%><%="'>后一篇</a></span>"%>
                        <%}%>
                    </div>
                </div>
                <%}
                    }%>
                <div class="main_content_right">
                    <div class="information"> 
                        <div class="title">个人资料</div>
                        <div> <img id = "photo" src="<%=author.getPhoto()%>" alt="picture is not available"/>
                            <div id="bloggerName"><%=author.getName()%></div>
                        </div>
                        <div id="introduction"><%=author.getIntroduction()%></div>
                    </div>
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
                   response.sendRedirect("login.jsp");
                }%>
    </body>
</html>
