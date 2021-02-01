package web.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao  {

    List<Role> getAllRoles();

    User getUserByName(String name);

    Role getRoleByName(String role);

    Role getRoleById(long id);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
