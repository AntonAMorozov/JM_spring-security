package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<Role> getAllRoles() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public User getUserByName(String username) {
        return sessionFactory.getCurrentSession().createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
    }

    @Override
    public Role getRoleById(long id) {

        return sessionFactory.getCurrentSession().createQuery("from Role where id = '" + id + "'", Role.class).getSingleResult();
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();

    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        session.saveOrUpdate(user);
    }

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }
}

