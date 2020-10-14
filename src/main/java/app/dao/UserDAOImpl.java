package app.dao;

import app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(User user) throws ParseException {
        Session session = sessionFactory.getCurrentSession();
        String date = user.getStringDateOfBirth();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date userDate = simpleDateFormat.parse(date);
        user.setDateOfBirth(userDate);
        return (int) session.save(user);
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return (User) session.createQuery("from User where email =:email")
                    .setParameter("email", email).getSingleResult();
        }catch (NoResultException exception){
            log.error(exception.getMessage(), exception);
            return null;
        }
    }

    @Override
    public User findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        String date = user.getStringDateOfBirth();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date userDate = null;
        try {
            userDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setDateOfBirth(userDate);
        session.update(user);
    }
}
