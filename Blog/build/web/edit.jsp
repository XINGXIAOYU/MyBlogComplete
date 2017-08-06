<%-- 
    Document   : edit
    Created on : 2015-6-10, 21:52:06
    Author     : xingxiaoyu
--%>
<%@page import="information.EssayClass"%>
<%@page import="information.Essay"%>
<%@page import="java.util.*"%>
<%@page import="information.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
        <link rel="stylesheet" type="text/css"
              href="css/style4.css"/>
        <script src="js/jquery.js"></script>
    </head>
    <body class="edit">
        <%if ((User) session.getAttribute("user") != null) {%>
        <div class="wrapper">
            <div id="to-top"></div>
            <div class="container">
                <div class="menu"> 
                    <!--the nav of the blog-->
                    <ul>
                        <li id="blog_main"><a href="main.jsp">首页</a> </li>
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
                <h3>发博文</h3>
                <form id="myForm" action="EssayServlet" method="post">
                    <div id="article_name">
                        <h4 class="mtitle">标题:</h4>
                        <input type="text" name="biaoti" id="biaoti" value=""/>
                    </div>
                    <div id="article_content">
                        <h4 class="mtitle">博文内容:</h4>
                        <textarea id="textarea" name="textarea"></textarea>
                    </div>
                    <div id="category">
                        <h4 class = "mtitle">分类:</h4>
                        <select name="fenlei" id="fenlei">
                            <option value ="未分类博文" selected="selected" name="未分类博文">未分类博文</option>
                            <%ArrayList<EssayClass> essayClass = (ArrayList<EssayClass>) session.getAttribute("essayClassList");%>
                            <%for (int i = 0; i < essayClass.size(); i++) {
                                    if (essayClass.get(i).getClassName().equals("未分类博文") == false) {
                            %>

                            <%="<option " + "value=" + essayClass.get(i).getClassName() + ">" + essayClass.get(i).getClassName() + "</option>"%>
                            <%}
                                }%>
                        </select>

                        <div>
                            <span id="createClass"><a>创建分类</a></span>
                            <input type="text" name="class" id="class" value="" placeholder="填写新的分类" style="display:none;">
                        </div>
                    </div>
                    <div class="tag">
                        <h4 class = "mtitle">标签:</h4>
                        <input type = "text" name = "biaoqian" id="biaoqian" placeholder="填写标签让更多人看到你的博文（用空格分隔）" value=""/>
                    </div>                      

                    <div id="fabowen">
                        <a>发博文</a>
                    </div>
                </form>
            </div>
        </div>
        <script src="js/checkEdit.js"></script>
        <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"></span></a>
            <%} else {
                 response.sendRedirect("index.jsp");
             }%>
    </body>
</html>
