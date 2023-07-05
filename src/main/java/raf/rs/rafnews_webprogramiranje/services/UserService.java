package raf.rs.rafnews_webprogramiranje.services;

import raf.rs.rafnews_webprogramiranje.entities.User;
import raf.rs.rafnews_webprogramiranje.repositories.user.UserRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public User addUser(User user) throws SQLException { return this.userRepository.addUser(user); }

    public List<User> allUsers() throws SQLException {
        return this.userRepository.allUsers();
    }
    public void updateUser(int id, String newEmail, String name, String surname, boolean admin, boolean status, String password) throws SQLException {
        this.userRepository.updateUser(id, newEmail, name, surname, admin, status, password);
    }

    public User findUserByEmail(String email) throws SQLException{
        return this.userRepository.findUserByEmail(email);
    };
    public User findUser(int id) throws SQLException{
        return this.userRepository.findUser(id);
    };


}
