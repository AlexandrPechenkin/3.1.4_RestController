package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;

@Controller
public class UsersController {

    final
    UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String infoUser(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "user-page";
    }
}
