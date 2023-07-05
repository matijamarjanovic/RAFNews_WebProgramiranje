package raf.rs.rafnews_webprogramiranje.repositories.user;

import raf.rs.rafnews_webprogramiranje.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    public User addUser(User user) throws SQLException;
    public List<User> allUsers() throws SQLException;
    public User findUserByEmail(String email) throws SQLException;
    public User findUser(int id) throws SQLException;
    public void updateUser(int id, String email, String name, String surname, boolean admin, boolean statusActive, String password) throws SQLException;


}
