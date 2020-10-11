package app.service;

import app.dao.UserDAO;
import app.model.User;
import app.model.enums.UserRole;
import app.model.enums.UserStatus;
import app.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    private final UserDAO userDAO;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("Unknown user: " + email);
        return SecurityUser.fromUser(user);
    }

    @Override
    @Transactional
    public User findById(int userId) {
        return userDAO.findById(userId);
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public List<User> allUsers() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public boolean saveUser(User user){
        User userFromDB = userDAO.findByEmail(user.getEmail());

        if (userFromDB != null) {
            return false;
        }

        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            userDAO.add(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public List<User> userList(int idMin) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
