/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import information.Comment;
import information.Essay;
import information.EssayClass;
import information.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xingxiaoyu
 */
public class EssayAct {

    //编辑页面
    //保存博文
    public void saveEssay(Essay essay) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into essay(user_id,essay_name,pbtime,content,classname) values(?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay.getUser_id());
            ps.setString(2, essay.getEname());
            ps.setString(3, essay.getPbtime());
            ps.setString(4, essay.getContent());
            ps.setString(5, essay.getClassName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    public void updateEssay(Essay essay) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update essay set `essay_name` = '" + essay.getEname() + "',`content` = '" + essay.getContent() + "',`classname` = '" + essay.getClassName() + "',`pbtime` = '" + essay.getPbtime() + "' where `essay_id` = " + essay.getEssay_id() + ";";
        try {
            Statement stm = (Statement) conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserAct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    public boolean mapExist(int tag_id, int essay_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from trans where tag_id = ? and essay_id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tag_id);
            ps.setInt(2, essay_id);
            System.out.println(tag_id + " " + essay_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return true;
    }

    //存储分类下博文的数量
    public void EssaySum(EssayClass essayClass, User user) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update class set essayNum='" + essayClass.getEssayNum() + "' where classname='" + essayClass.getClassName() + "' and user_id='" + user.getId() + "';";
        try {
            Statement stm = (Statement) conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    //创建文章分类
    public void saveEssayClass(User user, EssayClass essayClass) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into class(classname,user_id,essayNum) values(?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, essayClass.getClassName());
            ps.setInt(2, user.getId());
            ps.setInt(3, essayClass.getEssayNum());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    //检查分类名是否存在
    public boolean isEssayClassExist(String className, User user) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from class where className = ? and user_id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, className);
            ps.setInt(2, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return true;
    }

    //读取分类博文
    public void classify(EssayClass essayClass, int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where classname = ? and user_id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, essayClass.getClassName());
            ps.setInt(2, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Essay essay = new Essay();
                essay.setEssay_id(rs.getInt("essay_id"));
                essay.setClassName(rs.getString("classname"));
                essay.setUser_id(rs.getInt("user_id"));
                essay.setPbtime(rs.getString("pbtime"));
                essay.setContent(rs.getString("content"));
                essay.setEname(rs.getString("essay_name"));
                essay.setLike(rs.getInt("like"));
                essay.setCommentNum(rs.getInt("commentNum"));
                essayClass.getEssays().add(essay);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    //****************************************************************
    //保存，读取文章标签的方法
    public void saveTag(String str) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into tag(tag) values(?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, str);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    public void tagEssayMap(int tag_id, int essay_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into trans(tag_id,essay_id) values(?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tag_id);
            ps.setInt(2, essay_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    public boolean isTagNameExist(String tagName) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tag where tag = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tagName);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return true;
    }

    public int getTagId(String tagName) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tag where tag = ?;";
        int tag_id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tagName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tag_id = rs.getInt("tag_id");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return tag_id;
    }

    public int getEssayId(String essayname, int user_id, String pb_time) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where essay_name = ? and user_id = ? and pbtime =?;";
        int essay_id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, essayname);
            ps.setInt(2, user_id);
            ps.setString(3, pb_time);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                essay_id = rs.getInt("essay_id");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return essay_id;
    }

    public String getAuthorName(int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tb_user where user_id = ?;";
        String authorName = "";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                authorName = rs.getString("idName");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return authorName;
    }

    //储存阅读量
    public void savereading(int essay_id, int read) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update essay set `read` = " + read + " where `essay_id` = " + essay_id + ";";
        try {
            Statement stm = (Statement) conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserAct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    //sort
    //倒序
    public ArrayList<Essay> sortDateResultDesc(ArrayList<Essay> target) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < target.size(); i++) {
            list.add(target.get(i).getPbtime());
        }
        try {
            list = sortListDesc(list);
        } catch (ParseException ex) {
            Logger.getLogger(EssayAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Essay> result = new ArrayList<Essay>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (list.get(i).equals(target.get(j).getPbtime())) {
                    result.add(target.get(j));
                }
            }
        }
        return result;
    }

    public List<String> sortListDesc(List<String> list) throws ParseException {
        List<String> retStr = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Long, String> map = new TreeMap<Long, String>();
        for (int i = 0; i < list.size(); i++) {
            String dateStr = list.get(i);
            map.put(sdf.parse(dateStr).getTime(), dateStr);
        }
        Collection<String> coll = map.values();
        retStr.addAll(coll);
        Collections.reverse(retStr);
        return retStr;
    }

    //正序
    public ArrayList<Essay> sortDateResult(ArrayList<Essay> target) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < target.size(); i++) {
            list.add(target.get(i).getPbtime());
        }
        try {
            list = sortList(list);
        } catch (ParseException ex) {
            Logger.getLogger(EssayAct.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Essay> result = new ArrayList<Essay>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (list.get(i).equals(target.get(j).getPbtime())) {
                    result.add(target.get(j));
                }
            }
        }
        return result;
    }

    public List<String> sortList(List<String> list) throws ParseException {
        List<String> retStr = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Long, String> map = new TreeMap<Long, String>();
        for (int i = 0; i < list.size(); i++) {
            String dateStr = list.get(i);
            map.put(sdf.parse(dateStr).getTime(), dateStr);
        }
        Collection<String> coll = map.values();
        retStr.addAll(coll);
        return retStr;
    }

    public User getAuthor(int essay_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where essay_id = ?";
        int author_id = 0;
        User author = new User();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author_id = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (author_id != 0) {
            author = Author(author_id);
        }
        return author;
    }

    public User Author(int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tb_user where user_id=?;";
        User user = new User();
        UserAct act = new UserAct();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setEssay(act.readEssays(rs.getInt("user_id")));
                user.setEssayClass(act.readEssayClass(rs.getInt("user_id")));
                user.setIdName(rs.getString("idName"));
                user.setIntroduction(rs.getString("introduction"));
                user.setPhoto(rs.getString("photo"));
                user.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);

        }
        return user;
    }

    public int getEssayRead(int essay_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where essay_id = ?;";
        int read = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                read = rs.getInt("read");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return read;
    }

    public void saveComment(Comment comment) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into comment(essay_id,user_id,content,time) values(?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(2, comment.getUser_id());
            ps.setInt(1, comment.getEssay_id());
            ps.setString(3, comment.getContent());
            ps.setString(4, comment.getTime());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    public boolean tagInput(String[] str) {
        boolean repeat = false;
        for (int i = 0; i < str.length; i++) {
            if (i != str.length - 1 && str[i].equals(str[i + 1])) {
                repeat = true;
            }
        }
        return repeat;
    }

    public void deletetagEssayMap(int essay_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "delete from trans where essay_id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay_id);       
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
