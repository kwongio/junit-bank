package com.example.bank.jwt;

import com.example.bank.domain.user.Role;
import com.example.bank.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class JwtAuthorizationFilterTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void authorization_success_test() throws Exception {
        User user = User.builder().id(1L).role(Role.CUSTOMER).build();
        LoginUser loginUser = new LoginUser(user);
        String s = Jwt.create(loginUser);
        System.out.println("테스트 = " + s);
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/s/hello").header("Authorization", Jwt.PREFIX + s));
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
        System.out.println(perform.andReturn().getResponse().getStatus());
        System.out.println(perform);

        perform.andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}