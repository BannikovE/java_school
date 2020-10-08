package app.dao;

import app.model.User;

public interface UserDAO {
    int add(User user);
    User getByEmail(String email);
}
