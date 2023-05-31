package com.example.bank.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

@AutoConfigureMockMvc // Mock(가짜 환경에 MockMvc가 등록됨
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void authentcation_test() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/s/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int status = resultActions.andReturn().getResponse().getStatus();
        System.out.println("테스트: " + responseBody);
        System.out.println("상태코드: " + status);

    }


    @Test
    public void authorization_test() throws IOException {

    }

}