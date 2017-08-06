package operation;

import java.sql.*;

public class ConnectDB {
    //get connection to database
    static final String url = "jdbc:mysql://localhost:3306/MyBlog?useUnicode=true&characterEncoding=UTF-8";
    static final String username = "root";
    static final String password = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, username,
                    password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //close the connection with database

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
