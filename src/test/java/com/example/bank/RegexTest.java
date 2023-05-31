package com.example.bank;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void 한글만된다() throws IOException {
        String value = "가나";
        boolean result = Pattern.matches("^[가-힣]{2,20}$", value);
        System.out.println("테스트: " + result);

    }


    @Test
    public void 한글은_안된다() throws IOException {
        String value = "sad";
        boolean result = Pattern.matches("^[^ㄱ-ㅎ가-힣]{2,4}+$", value);
        System.out.println("테스트: " + result);

    }

    @Test
    public void userUsernameTest() throws IOException {
        String username = "ssar";
        boolean matches = Pattern.matches("^[a-zA-Z0-9]{2,20}$", username);
    }

    @Test
    public void email() throws IOException {
        String email = "ssar@nate.com";
        boolean matches = Pattern.matches("^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,20}$", email);


    }
}
