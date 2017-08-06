<%-- 
    Document   : deleteComment
    Created on : 2015-6-23, 20:40:05
    Author     : xingxiaoyu
--%>
<%@page import="information.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="operation.EssayAct"%>
<%@page import="operation.UserAct"%>
<%@page import="information.User"%>
<%@page import="operation.ConnectDB"%>
<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User) session.getAttribute("user");
    int comment_id = Integer.parseInt(request.getParameter("comment_id"));
    int essay_id = Integer.parseInt(request.getParameter("essay_id"));
    String sql = "delete from comment where `comment_id` = " + comment_id + ";";
    Connection conn = ConnectDB.getConnection();
    EssayAct act = new EssayAct();
    User author = new User();
    author = act.getAuthor(essay_id);
    try {
        Statement stm = (Statement) conn.createStatement();
        stm.executeUpdate(sql);
        stm.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    ArrayList<Comment> comments = new ArrayList<Comment>();
    String sql2 = "select * from comment where essay_id = " + essay_id + ";";
    try {
        Statement stm = (Statement) conn.createStatement();
        ResultSet rs = stm.executeQuery(sql2);
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
    <%if ((user != null && comments.get(m).getAuthor(comments.get(m).getUser_id()).getIdName().equals(user.getIdName())) || user.getIdName().equals(author.getIdName())) {%>
    <span class='delete'><%="<a onclick ='deleteC("%><%=comments.get(m).getComment_id() + "," + essay_id%><%=")'>"%>删除<%="</a>"%></span>
    <%}%>
     
</div><div class="clear"></div>
<%}
    }%>

