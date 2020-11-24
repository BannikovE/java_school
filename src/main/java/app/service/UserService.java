package app.service;

import app.model.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface UserService {
    boolean saveUser(User user, HttpServletRequest request);
    User findById(int userId);
    User findByEmail(String email);
    List<User> allUsers();
    User edit(User user, HttpServletRequest request);
}
