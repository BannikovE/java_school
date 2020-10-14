package app.dao;

import app.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserDAO {
    int add(User user) throws ParseException;
    User findByEmail(String email);
    User findById(int id);
    List<User> findAll();
    void edit(User user);
}
