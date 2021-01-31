package web.service;



import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    User getUserByName(String username);

    Role getRoleByName(String role);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(long id);

    void deleteUser(long id);

}
