package ru.kata.spring_boot_security_bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_boot_security_bootstrap.model.Role;
import ru.kata.spring_boot_security_bootstrap.model.User;
import ru.kata.spring_boot_security_bootstrap.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
        User userFromDB = userService.findById(user.getId());
        model.addAttribute("userAuth", userFromDB);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new User());

        return "mainPage";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);

        if (user.getRoles().contains(2)) {
            return "redirect:/admin";
        }
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/new")
    public String newUserPost(@ModelAttribute("user") User user, @RequestParam("role") String roleName) {
        Role role = userService.findRoleByName(roleName);
        user.setRoles(Set.of(role));
        userService.save(user);
        return "redirect:/admin";
    }


    @PatchMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("role") String roleName) {
        userService.update(user, roleName);
        return "redirect:/admin";
    }
}
