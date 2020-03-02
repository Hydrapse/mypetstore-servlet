package org.csu.mypetstore.persistence;

import java.sql.*;

public class DBUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/servlet_store?useSSL=false&serverTimezone=GMT%2B8" +
            "&allowPublicKeyRetrieval=true";
    private static String username = "Ikalos";
    private static String password = "123456";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

//    public static void main(String[] args) {
////        Connection con = DBUtil.getConnection();
////        System.out.println(con);
////    }

}

