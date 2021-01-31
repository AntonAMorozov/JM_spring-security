package web.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao  {

    User getUserByName(String name);
    Role getRoleByName(String role);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
