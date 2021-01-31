package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "user", method = RequestMethod.GET)
//    public String welcomeUser() {
//        return "user";
//    }

    @GetMapping("user")
    public String showAllUsers(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("message", "You are logged in as " + principal.getName());
        model.addAttribute("userInfo", user);
        return "user-data";
    }



}

