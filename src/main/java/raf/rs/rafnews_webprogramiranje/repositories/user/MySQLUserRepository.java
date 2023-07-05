package raf.rs.rafnews_webprogramiranje.repositories.user;

import raf.rs.rafnews_webprogramiranje.entities.User;
import raf.rs.rafnews_webprogramiranje.repositories.MySQLAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserRepository extends MySQLAbstractRepository implements UserRepository {
    @Override
    public User addUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();

        String[] generatedColumns = {"id"};

        statement = connection.prepareStatement("INSERT INTO Users (email, name, surname, admin, statusActive, password) VALUES (?, ?, ?, ?, ?, ?)", generatedColumns);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setBoolean(4, user.isAdmin());
        statement.setBoolean(5, user.isStatus());
        statement.setString(6, user.getHashedPassword());

        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            user.setId(resultSet.getInt(1));
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return user;
    }

    @Override
    public List<User> allUsers() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<User> allUsers = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        String query = "SELECT * from users";
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            allUsers.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getBoolean("admin"),
                    resultSet.getBoolean("statusActive"),
                    resultSet.getString("password")
            ));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allUsers;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();
        statement = connection.prepareStatement("SELECT * FROM users where email like " + '"' + email + '"');

        resultSet = statement.executeQuery();

        if(resultSet.next()){
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getBoolean("admin"),
                    resultSet.getBoolean("statusActive"),
                    resultSet.getString("password")
            );
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

            return user;
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return null;
    }

    @Override
    public User findUser(int id) throws SQLException {
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();
        statement = connection.prepareStatement("SELECT * FROM users where id = " + '"' + id + '"');

        resultSet = statement.executeQuery();

        if(resultSet.next()){
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getBoolean("admin"),
                    resultSet.getBoolean("statusActive"),
                    resultSet.getString("password")
            );
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

            return user;
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return null;
    }

    @Override
    public void updateUser(int id, String email, String name, String surname, boolean admin, boolean statusActive, String password) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<User> allUsers = this.allUsers();

        for (User u: allUsers){
            if(u.getId() == id)
                return;
        }

        connection = this.newConnection();
        statement = connection.createStatement();
        preparedStatement = statement.getConnection().prepareStatement("UPDATE users SET email = " + '"'+ email  + '"' + " , name = " + '"' +  name + '"' + " , surname = " + '"' +  surname + '"' +" , admin = " + '"' +  admin + '"' +" , statusActive = " + '"' +  statusActive + '"' +" , password = " + '"' +  password + '"' +  " WHERE id like " + '"'+ id + '"');
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);
    }
}
