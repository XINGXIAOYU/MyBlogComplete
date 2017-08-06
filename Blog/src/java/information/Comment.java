/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import operation.ConnectDB;

/**
 *
 * @author xingxiaoyu
 */
public class Comment {
    private int comment_id;
    private User Author;
    private int essay_id;
    private int user_id;
    private String content;
    private String time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public User getAuthor(int user_id){
        Connection conn = ConnectDB.getConnection();
        String sql = "select * from tb_user where user_id = ?";
        User author = new User();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               author = new User();
               author.setIdName(rs.getString("idName"));
               author.setName(rs.getString("name"));
               author.setPhoto(rs.getString("photo"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return author;
    }
    
    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getEssay_id() {
        return essay_id;
    }

    public void setEssay_id(int essay_id) {
        this.essay_id = essay_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
