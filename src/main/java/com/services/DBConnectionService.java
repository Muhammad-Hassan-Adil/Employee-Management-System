package com.services;
import com.zaxxer.hikari.*;
import java.sql.Connection;
import java.sql.SQLException;
public class DBConnectionService {
    private static HikariDataSource dataSource;
    public static Connection getConnection()throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/Employee");
        config.setUsername("root");
        config.setPassword("#Pirates20");
        dataSource = new HikariDataSource(config);
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConnection() {
        dataSource.close();
    }
}

