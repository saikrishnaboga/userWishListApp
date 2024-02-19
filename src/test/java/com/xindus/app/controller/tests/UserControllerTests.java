package com.xindus.app.controller.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindus.user.app.controller.UserController;
import com.xindus.user.app.entity.AuthRequest;
import com.xindus.user.app.entity.UserInfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void welcome() throws Exception {
        mockMvc.perform(get("/auth/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Spring Security tutorials !!"));
    }

    @Test
    void addUser() throws Exception {
        UserInfo userInfo = new UserInfo();

        mockMvc.perform(post("/auth/addUser")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userInfo)))
                .andExpect(status().isOk())
                .andExpect(content().string("User added successfully"));
    }

    @Test
    void login() throws Exception {
        AuthRequest authRequest = new AuthRequest();

        mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ADMIN_ROLES")
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/auth/getUsers"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "USER_ROLES")
    void getUserById() throws Exception {
        mockMvc.perform(get("/auth/getUsers/{id}", 1))
                .andExpect(status().isOk());
    }
}
