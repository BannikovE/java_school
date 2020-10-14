package app.service;

import app.cache.CartCache;
import app.dao.UserDAO;
import app.model.User;
import app.model.cart.Cart;
import app.model.enums.UserRole;
import app.model.enums.UserStatus;
import app.security.SecurityUser;
import app.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    public static final Logger log = LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager entityManager;
    private final UserDAO userDAO;

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
    public boolean saveUser(User user, HttpServletRequest request) throws ParseException {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            log.info("User with login {} already exist.", user.getEmail());
            return false;
        }
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Integer userId = userDAO.add(user);
        //При успешной авторизации, если есть данные в корзине, перемещаем их в кэш, чтоб не потерять
        if (userId != null) {
            Cart cart = Utils.getCartFromCookie(request);
            if (cart != null) {
                CartCache.getInstance().put(userId, cart);
            }
        }
        return userId != null;
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

    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }
}
