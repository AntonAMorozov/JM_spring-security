package web.dao;


import web.model.Role;
import web.model.User;

import java.util.List;


public interface UserDao  {

    List<Role> getAllRoles();

    User getUserByName(String name);


    Role getRoleById(long id);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
