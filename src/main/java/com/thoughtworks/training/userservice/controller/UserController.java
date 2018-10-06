package com.thoughtworks.training.userservice.controller;

import com.thoughtworks.training.userservice.exception.ConflictException;
import com.thoughtworks.training.userservice.exception.NotFoundException;
import com.thoughtworks.training.userservice.model.User;
import com.thoughtworks.training.userservice.service.UserService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) throws NotFoundException {
        String token = userService.login(user);
        response.addHeader("token", token);
        return token;
    }

    @PostMapping("/registration")
    public User register(@RequestBody User user) throws ConflictException {
        return userService.register(user);
    }

    @GetMapping("/verification")
    public User verify() {
        return userService.verify();
    }
}
