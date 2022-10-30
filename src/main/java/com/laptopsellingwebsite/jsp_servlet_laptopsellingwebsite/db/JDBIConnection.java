package com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;

import static com.laptopsellingwebsite.jsp_servlet_laptopsellingwebsite.db.DBProperties.*;


public class JDBIConnection {
    private static Jdbi jdbi;

    private static void makeConnect(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + getDbHost() + ":" + getDbPort() + "/" + getDbName());
        dataSource.setUser(getUserName());
        dataSource.setPassword(getPassWord());
        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        jdbi = Jdbi.create(dataSource);
    }

    public JDBIConnection() {
    }

    public static Jdbi get(){
        if (jdbi == null) makeConnect();
        return jdbi;
    }
}
