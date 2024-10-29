package com.example.democrud.controller;

import com.example.democrud.model.User;
import com.example.democrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("/user-add")
    public String addUserPage(@ModelAttribute("user") User user) {

        return "user-add";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/update-page/{id}")
    public String updateUserPage(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "user-update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
