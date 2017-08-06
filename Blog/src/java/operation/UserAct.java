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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class UserAct {

    public void update(User user, HttpServletRequest request) {
        user.getEssayClass().clear();
        user.getEssay().clear();
        user.setEssayClass(readEssayClass(user.getId()));
        user.setEssay(readEssays(user.getId()));
        request.getSession().setAttribute("essayClassList", user.getEssayClass());
        request.getSession().setAttribute("essayList", user.getEssay());
    }

    public ArrayList<EssayClass> readEssayClass(int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from class where user_id = ?";
        EssayAct essayAct = new EssayAct();
        ArrayList<EssayClass> list = new ArrayList<EssayClass>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EssayClass essayClass = new EssayClass();
                essayClass.setClass_id(rs.getInt("class_id"));
                essayClass.setClassName(rs.getString("className"));
                essayClass.setEssayNum(rs.getInt("essayNum"));
                essayAct.classify(essayClass, user_id);//读取分类下的博文
                list.add(essayClass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    //读取用户所有文章
    public ArrayList<Essay> readEssays(int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where user_id = ? order by pbtime";
        ArrayList<Essay> essays = new ArrayList<Essay>();
        EssayAct act = new EssayAct();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Essay essay = new Essay();
                essay.setEssay_id(rs.getInt("essay_id"));
                essay.setUser_id(rs.getInt("user_id"));
                essay.setEname(rs.getString("essay_name"));
                essay.setClassName(rs.getString("classname"));
                essay.setCommentNum(rs.getInt("commentNum"));
                essay.setContent(rs.getString("content"));
                essay.setLike(rs.getInt("like"));
                essay.setPbtime(rs.getString("pbtime"));
                essay.setAuthorName(act.getAuthorName(rs.getInt("user_id")));
                essay.setComment(readComment(rs.getInt("essay_id")));
                essay.setRead(rs.getInt("read"));
                if (!readTags(rs.getInt("essay_id")).isEmpty()) {
                    essay.setTags(readTags(rs.getInt("essay_id")));
                }
                essays.add(essay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return essays;
    }

    public ArrayList<Comment> readComment(int essay_id) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from comment where essay_id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setComment_id(rs.getInt("comment_id"));
                comment.setEssay_id(rs.getInt("essay_id"));
                comment.setContent(rs.getString("content"));
                comment.setTime(rs.getString("time"));
                comment.setUser_id(rs.getInt("user_id"));
                comments.add(comment);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return comments;

    }

    public User login(String idName, String password, HttpServletRequest request) {
        User user = null;
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tb_user where idName = ? and password = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            // if find the email&password,then return that user's information
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setIdName(rs.getString("idName"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setIntroduction(rs.getString("introduction"));
                user.setPhoto(rs.getString("photo"));
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("user_id", rs.getInt("user_id"));
                request.getSession().setAttribute("idName", rs.getString("idName"));
                request.getSession().setAttribute("name", rs.getString("name"));
                request.getSession().setAttribute("introduction", rs.getString("introduction"));
                request.getSession().setAttribute("photo", rs.getString("photo"));
            }
            if (user != null) {
                user.setEssayClass(readEssayClass(user.getId()));
                user.setEssay(readEssays(user.getId()));
                request.getSession().setAttribute("essayClassList", readEssayClass(user.getId()));
                request.getSession().setAttribute("essayList", readEssays(user.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }

    public void in(int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update tb_user set state = 1 where user_id = " + user_id + ";";
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

    public void out(int user_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update tb_user set state = 0 where user_id = " + user_id + ";";
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

    public boolean idNameIsExist(String idName) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tb_user where idName = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idName.trim());
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

    public void saveUser(User user) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into tb_user(idName,name,password) values(?,?,?);";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getIdName());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    public void defaultClass(User user, String className) {
        EssayClass class1 = new EssayClass(className);
        user.getEssayClass().add(class1);
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "insert into class(className,user_id) values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, className);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserAct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    public void iLike(User user, Essay essay) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update essay set like='" + essay.getLike() + "';";
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

    //游客登录
    public void tourist(String str, HttpServletRequest request) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where essay_id=?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(str));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("user_id");
                downloadUser(user_id, request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);

        }
    }

    //加载用户信息
    public void downloadUser(int user_id, HttpServletRequest request) {
        Connection conn1 = (Connection) ConnectDB.getConnection();
        String sql = "select * from tb_user where user_id=?;";
        try {
            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                request.getSession().setAttribute("user_id", rs.getInt("user_id"));
                request.getSession().setAttribute("idName", rs.getString("idName"));
                request.getSession().setAttribute("name", rs.getString("name"));
                request.getSession().setAttribute("introduction", rs.getString("introduction"));
                request.getSession().setAttribute("photo", rs.getString("photo"));
                request.getSession().setAttribute("essayClassList", readEssayClass(rs.getInt("user_id")));
                request.getSession().setAttribute("essayList", readEssays(rs.getInt("user_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn1);

        }
    }

    //加载标签信息
    public ArrayList readTags(int essay_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from trans where essay_id = ?;";
        ArrayList tags = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tags.add(getTagName(rs.getInt("tag_id")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return tags;
    }

    public String getTagName(int tag_id) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from tag where tag_id = ?;";
        String tagName = "";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tag_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tagName = rs.getString("tag");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return tagName;
    }

    //************************************************
    //读取文章具体内容
    public Essay getEssayDetail(int essay_id) {
        Essay essay = new Essay();
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay where essay_id=?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, essay_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                essay.setUser_id(rs.getInt("user_id"));
                essay.setClassName(rs.getString("classname"));
                essay.setEname(rs.getString("essay_name"));
                essay.setContent(rs.getString("content"));
                essay.setPbtime(rs.getString("pbtime"));
                essay.setCommentNum(rs.getInt("commentNum"));
                essay.setLike(rs.getInt("like"));
                essay.setRead(rs.getInt("read"));
                essay.setTags(readTags(essay_id));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return essay;
    }
//搜索

    public ArrayList<Essay> search(int user_id, int keyword, String request) {
        ArrayList<Essay> allEssay = readEssays(user_id);
        ArrayList<Essay> newEssays = new ArrayList<Essay>();
        for (int i = 0; i < allEssay.size(); i++) {
            if (keyword == 1) {
                if (allEssay.get(i).getEname().contains(request)) {
                    newEssays.add(allEssay.get(i));
                }
            } else if (keyword == 2) {
                if (allEssay.get(i).getContent().contains(request)) {
                    newEssays.add(allEssay.get(i));
                }
            } else if (keyword == 3) {
                ArrayList tags = allEssay.get(i).getTags();
                for (int j = 0; j < tags.size(); j++) {
                    if (tags.get(j).toString().contains(request)) {
                        newEssays.add(allEssay.get(i));
                    }
                }
            }
        }
        return newEssays;
    }

    public ArrayList<Essay> search(int keyword, String request) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "select * from essay";
        ArrayList<Essay> allEssay = new ArrayList<Essay>();
        ArrayList<Essay> newEssays = new ArrayList<Essay>();
        EssayAct act = new EssayAct();
        try {
            Statement stm = (Statement) conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Essay essay = new Essay();
                essay.setEssay_id(rs.getInt("essay_id"));
                essay.setUser_id(rs.getInt("user_id"));
                essay.setEname(rs.getString("essay_name"));
                essay.setClassName(rs.getString("classname"));
                essay.setCommentNum(rs.getInt("commentNum"));
                essay.setContent(rs.getString("content"));
                essay.setLike(rs.getInt("like"));
                essay.setPbtime(rs.getString("pbtime"));
                essay.setAuthorName(act.getAuthorName(rs.getInt("user_id")));
                essay.setComment(readComment(rs.getInt("essay_id")));
                if (!readTags(rs.getInt("essay_id")).isEmpty()) {
                    essay.setTags(readTags(rs.getInt("essay_id")));
                }
                allEssay.add(essay);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserAct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        for (int i = 0; i < allEssay.size(); i++) {
            if (keyword == 1) {
                if (allEssay.get(i).getEname().contains(request)) {
                    newEssays.add(allEssay.get(i));
                }
            } else if (keyword == 2) {
                if (allEssay.get(i).getContent().contains(request)) {
                    newEssays.add(allEssay.get(i));
                }
            } else if (keyword == 3) {
                ArrayList tags = allEssay.get(i).getTags();
                for (int j = 0; j < tags.size(); j++) {
                    if (tags.get(j).toString().contains(request)) {
                        newEssays.add(allEssay.get(i));
                    }
                }
            }
        }
        return newEssays;
    }

    public void updateUser(int user_id, String newName, String password) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update tb_user set name  = '" + newName + "',password = '" + password + "' where user_id = " + user_id + ";";
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

    public void updatePhoto(int user_id, String url) {
        Connection conn = (Connection) ConnectDB.getConnection();
        String sql = "update tb_user set photo  = '" + url + "'where user_id = " + user_id + ";";
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

}
