package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.Query;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {


//    private final Map<String, User> userMap = Collections.singletonMap(
//            "admin", new User(1L, "admin", "$2a$12$BxKZw/UTmzLr742siFihmuRfCkki9cfcy77VEkxsRSb2KXNXcMkum", Collections.singleton(new Role(1L, "ROLE_ADMIN")))
//    );
//
//
//    UserRepository userRepository;

//    @Override
//    public User getUserByName(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//        System.out.println("1  UserDaoImpl, getUserByName");
//        return userMap.get(name);
//    }

//    @Override
//    public User getUserByName(String name) {
//        System.out.println("1  UserDaoImpl, getUserByName");
//        User user = userRepository.findByUsername(name);
////        Session session = sessionFactory.getCurrentSession();
////        User user = (User) session.createQuery("FROM User u where u.username = :name").setParameter("name", name).uniqueResult();
////        System.out.println(user.getUsername());
//        return user;
//    }
//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
    public Role getRoleByName(String role) {

        return sessionFactory.getCurrentSession().createQuery("from Role where role = '" + role + "'", Role.class).getSingleResult();
    }

    @Override
    public Role getRoleById(long id) {

        return sessionFactory.getCurrentSession().createQuery("from Role where id = '" + id + "'", Role.class).getSingleResult();
    }

    @Autowired
    // @PersistenceContext
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

