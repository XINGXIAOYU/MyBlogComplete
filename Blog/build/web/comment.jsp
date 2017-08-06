<%-- 
    Document   : comment
    Created on : 2015-6-23, 19:12:22
    Author     : xingxiaoyu
--%>

<%@page import="java.sql.*"%>
<%@page import="information.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="operation.ConnectDB"%>
<%@page import="operation.EssayAct"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="information.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User) session.getAttribute("user");
    Comment comment = new Comment();
    int essay_id = Integer.parseInt(request.getParameter("essay_id"));
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    String text = request.getParameter("content");
    comment.setEssay_id(essay_id);
    comment.setUser_id(user_id);
    comment.setContent(text);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    comment.setTime(df.format(new Date()));
    EssayAct act = new EssayAct();
    User author = new User();
    author = act.getAuthor(essay_id);
    act.saveComment(comment);
    ArrayList<Comment> comments = new ArrayList<Comment>();
    Connection conn = ConnectDB.getConnection();
    String sql = "select * from comment where essay_id = " + essay_id + ";";
    try {
        Statement stm = (Statement) conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Comment c = new Comment();
            c.setComment_id(rs.getInt("comment_id"));
            c.setEssay_id(rs.getInt("essay_id"));
            c.setUser_id(rs.getInt("user_id"));
            c.setTime(rs.getString("time"));
            c.setContent(rs.getString("content"));
            comments.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConnectDB.closeConnection(conn);
    }

%>
<%if (comments.size() != 0) {%>

<%for (int m = 0; m < comments.size(); m++) {%>
<div class="comment-info">
    <img src="<%=comments.get(m).getAuthor(comments.get(m).getUser_id()).getPhoto()%>" alt=""/>
    <h4><%=comments.get(m).getAuthor(comments.get(m).getUser_id()).getName()%></h4>
    <p><%=comments.get(m).getContent()%></p>
    <h5><%=comments.get(m).getTime()%></h5>
    <%if (user != null && comments.get(m).getAuthor(comments.get(m).getUser_id()).getIdName().equals(user.getIdName())) {%>
    <span class='delete'><%="<a onclick ='deleteC("%><%=comments.get(m).getComment_id() + "," + essay_id%><%=")'>"%>删除<%="</a>"%></span>
    <%}%>
   
</div> <div class="clear"></div>
<%}%>
<%}%>

