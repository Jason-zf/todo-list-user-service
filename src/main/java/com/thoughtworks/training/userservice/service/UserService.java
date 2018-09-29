package com.thoughtworks.training.userservice.service;

import com.thoughtworks.training.userservice.model.User;
import com.thoughtworks.training.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String login(User user) {
        return null;
    }
}
