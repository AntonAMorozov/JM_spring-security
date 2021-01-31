package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("admin")
    public String showAllUsers(Model model, Principal principal) {
        List<User> userList = userService.getAllUsers();
        System.out.println(userList.get(0));
        model.addAttribute("users", userList);
        model.addAttribute("message", "You are logged in as " + principal.getName());
        System.out.println(principal.getName());
        System.out.println(principal.toString());
        return "all-users";
    }

    @GetMapping("addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user-info";
    }

    @PostMapping("saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(2L));
//        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:admin";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String welcomeUser() {
        return "user";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("newUser", userService.getUser(id));
        return "user-info";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}