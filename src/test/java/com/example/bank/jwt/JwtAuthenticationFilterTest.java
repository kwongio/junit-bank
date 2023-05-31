package com.example.bank.jwt;

import com.example.bank.config.dummy.DummyObject;
import com.example.bank.domain.user.User;
import com.example.bank.domain.user.UserRepository;
import com.example.bank.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class JwtAuthenticationFilterTest extends DummyObject {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @Test
    public void setup() throws IOException {
        User save = userRepository.save(newUser("ssar", "쌀"));
    }

    @Test
    public void successfulAuthentication_test() throws Exception {

        LoginRequest loginRequest = new LoginRequest("ssar", "1234");
        String responseBody = objectMapper.writeValueAsString(loginRequest);
        System.out.println("테스트 : " + responseBody);

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(responseBody));
        String token = perform.andReturn().getResponse().getHeader("Authorization");
        System.out.println("token = " + token);
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
    }
}