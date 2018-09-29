package com.thoughtworks.training.userservice.service;

import com.thoughtworks.training.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String createToken(User user1) {
        return "token";
    }
}
