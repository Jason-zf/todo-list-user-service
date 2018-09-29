package com.thoughtworks.training.userservice.controller;

import com.thoughtworks.training.userservice.model.User;
import com.thoughtworks.training.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }
}
