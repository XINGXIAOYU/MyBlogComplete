/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import operation.ConnectDB;

/**
 *
 * @author xingxiaoyu
 */
public class Essay {
    private int user_id;
    private int essay_id;
    private String ename;
    private String pbtime;
    private String content;
    private ArrayList<Comment> comment = new ArrayList<Comment>();
    private String className;
    private String authorName;
    private int like;
    private int commentNum;
    private int read;
    private ArrayList<String> tags = new ArrayList<String>();

    public int getClassId(String className) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from class where className = ?;";
        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, className);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("class_id");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return id;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public ArrayList getTags() {
        return tags;
    }

    public void setTags(ArrayList tags) {
        this.tags = tags;
    }

    public int getEssay_id() {
        return essay_id;
    }

    public void setEssay_id(int essay_id) {
        this.essay_id = essay_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPbtime() {
        return pbtime;
    }

    public void setPbtime(String pbtime) {
        this.pbtime = pbtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

}
