package com.nexign.testUserService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexign.testUserService.entity.UserEntity;
import com.nexign.testUserService.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addUsersTest() throws Exception {
        UserEntity userEntity = new UserEntity(1L, "Alex", "123");
        String s = objectMapper.writeValueAsString(userEntity);
        mockMvc.perform(post("/v1/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userEntity.getLogin())));
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/v1/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
