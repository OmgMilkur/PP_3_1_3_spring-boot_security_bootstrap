package ru.kata.spring_boot_security_bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring_boot_security_bootstrap.model.User;
import ru.kata.spring_boot_security_bootstrap.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserInfo(@AuthenticationPrincipal User user, Model model) {
        User userFromDB = userService.findById(user.getId());
        model.addAttribute("userAuth", userFromDB);
        return "mainPage";
    }
}
