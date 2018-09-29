package com.thoughtworks.training.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.training.userservice.model.User;
import com.thoughtworks.training.userservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void shouldLoginGivenUser() throws Exception {
        User user = User.builder().name("xiaoming").password("123").build();
        given(userService.login(any())).willReturn("eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MX0.AJrfN1GLvJRNNuTQsTqI4tV6_MkV3chS0OVwubPSiCu8umfkTKtJ3fn3bpXr7tsscMMfE-jTi5YGvnZ_VEraCg");
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parToJson(user)))
                .andExpect(status().isOk());
        verify(userService, times(1)).login(user);
    }

    private String parToJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldRegisterGivenUser() throws Exception {
        User user = User.builder().name("xiaoming").password("123").build();
        given(userService.register(any())).willReturn(user);
        mockMvc.perform(post("/users/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parToJson(user)))
                .andExpect(status().isOk());
        verify(userService, times(1)).register(user);
    }

    @Test
    @WithMockUser
    public void shouldVerifyGivenToken() throws Exception {
        given(userService.verify()).willReturn(User.builder().id(1L).name("xiaoming").build());

        mockMvc.perform(get("/users/verification"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("xiaoming"));
        verify(userService, times(1)).verify();
    }
}