package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @WithMockUser(username = "JohnDoe", roles = {"USER", "ADMIN"})
    public void should_find_a_user_by_name() throws Exception {
        String userName = "JohnDoe";
        UserDTO userDTO = new UserDTO(userName);

        when(userService.findById(userName)).thenReturn(userDTO);

        mockMvc.perform(get("/api/v1/users/{username}", userName))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.userName").value(userName))
                .andExpect(status().isOk());

    }


    @Test
    @WithMockUser(roles  = {"USER"})
    void testPostMethod() throws Exception {
        mockMvc.perform(post("/api/v1/users/")
                        .contentType(APPLICATION_JSON)
                        .content("{ \"username\": \"test\", \"password\": \"password\" }"))
                .andExpect(status().isForbidden());
    }

}