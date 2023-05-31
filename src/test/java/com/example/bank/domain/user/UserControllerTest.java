package com.example.bank.domain.user;

import com.example.bank.config.dummy.DummyObject;
import com.example.bank.dto.user.JoinRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.InitBinder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Rollback
class UserControllerTest extends DummyObject {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private UserRepository userRepository;


    @InitBinder
    public void setUp() {
        dataSetting();
    }



    @Test
    public void join_test() throws Exception {
        JoinRequest joinRequest = new JoinRequest("love", "1232", "love@naver.com", "러브");

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/join")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(joinRequest)));

        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);

        perform.andExpect(status().isOk());

    }

    @Test
    public void join_fail() throws Exception {
        JoinRequest joinRequest = new JoinRequest("ssar", "1232", "sdfasdf@naver.com", "sdfsdf");

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/join")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(joinRequest)));

        String responseBody = perform.andReturn().getResponse().getContentAsString();
        System.out.println("contentAsString = " + responseBody);
        System.out.println(perform.andReturn().getResponse().getStatus());

    }

    private void dataSetting() {
        userRepository.save(newUser("ssar", "234234"));
    }

}