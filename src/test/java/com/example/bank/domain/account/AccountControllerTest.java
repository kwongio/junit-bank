package com.example.bank.domain.account;

import com.example.bank.config.dummy.DummyObject;
import com.example.bank.domain.user.UserRepository;
import com.example.bank.dto.AccountSaveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AccountControllerTest extends DummyObject {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        userRepository.save(newUser("ddd", "쌀"));
    }

    @WithUserDetails(value = "ddd", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void saveAccount_test() throws Exception {
        AccountSaveRequest accountSaveRequest = new AccountSaveRequest(9999L, 1234L);
        String requestBody = objectMapper.writeValueAsString(accountSaveRequest);
        ResultActions perform = mockMvc.perform(post("/api/account").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
    }

}