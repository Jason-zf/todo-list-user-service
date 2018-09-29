package com.thoughtworks.training.userservice.service;

import com.thoughtworks.training.userservice.exception.ConflictException;
import com.thoughtworks.training.userservice.exception.NotFoundException;
import com.thoughtworks.training.userservice.model.User;
import com.thoughtworks.training.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public String login(User user) throws NotFoundException {
        User user1 = userRepository.findByName(user.getName());
        if (user1 != null && user.getPassword().equals(user.getPassword())) {
            return tokenService.createToken(user1);
        }
        throw new NotFoundException();
    }

    public User register(User user) throws ConflictException {
        if (userRepository.findByName(user.getName()) == null) {
            return userRepository.save(user);
        }
        throw new ConflictException();
    }

    public User verify() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User getOne(Long id) {
        return userRepository.findOne(id);
    }
}
