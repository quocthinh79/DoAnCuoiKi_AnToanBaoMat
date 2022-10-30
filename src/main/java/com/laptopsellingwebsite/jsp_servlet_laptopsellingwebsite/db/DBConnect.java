package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    private static final String DB_URL = "jdbc:mysql://b2bc3f54045349:c554bfff@us-cdbr-east-06.cleardb.net/heroku_2c2a2f331ba4e21?reconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "b2bc3f54045349";
    private static final String PASSWORD = "c554bfff";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/laptopsellingwebsite?useUnicode=true&characterEncoding=utf-8";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
    private static DBConnect dbConnect;
    private static Connection connection;

    private DBConnect() {
    }

    public static DBConnect getInstance() {
        if (dbConnect == null) {
            dbConnect = new DBConnect();
        }
        return dbConnect;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        if ((connection == null) || connection.isClosed())
            Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private boolean isConnect() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public PreparedStatement get(String sql) throws SQLException, ClassNotFoundException {
        try {
            if (!isConnect())
                connect();
            return connection.prepareStatement(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
