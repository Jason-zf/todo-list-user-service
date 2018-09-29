package com.thoughtworks.training.userservice.security;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.training.userservice.model.User;
import com.thoughtworks.training.userservice.service.TokenService;
import com.thoughtworks.training.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token != null) {
            Long userId = tokenService.parseToken(token);
            User user = userService.getOne(userId);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(User.builder().id(userId).name(user.getName()).build(), "", ImmutableList.of()));
        }
        filterChain.doFilter(request, response);
    }
}
