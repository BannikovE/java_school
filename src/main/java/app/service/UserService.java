package app.service;

import app.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    boolean saveUser(User user) throws ParseException;
    User findById(int userId);
    User findByEmail(String email);
    List<User> allUsers();
    void deleteUser(User user);
    List<User> userList(int idMin);
}
