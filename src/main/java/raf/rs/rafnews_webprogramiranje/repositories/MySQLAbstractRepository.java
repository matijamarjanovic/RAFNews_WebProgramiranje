package raf.rs.rafnews_webprogramiranje.repositories;

import java.sql.*;
import java.util.Optional;

public abstract class MySQLAbstractRepository {
    public MySQLAbstractRepository() {
        System.out.println("DATABASE CONNECTION INITIATED");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected Connection newConnection() throws SQLException {
        System.out.println("DATABASE CONNECTED");
        return DriverManager.getConnection(
                "jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabaseName(), this.getUsername(), this.getPassword()
        );
    }
    private String getPassword() {
        return "MisterMasterJoda2001!";
    }

    private String getUsername() {
        return "root";
    }

    private String getDatabaseName() {
        return "rafsch";
    }

    private int getPort() {
        return 3306;
    }

    private String getHost() {
        return "localhost";
    }

    protected void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
