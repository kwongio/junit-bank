package com.example.bank.config.dummy;

import com.example.bank.domain.account.Account;
import com.example.bank.domain.user.Role;
import com.example.bank.domain.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class DummyObject {

    protected User newUser(String username, String fullName) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");

        return User.builder().username(username).password(encode).email(fullName + "@nate.com").fullName(fullName).role(Role.CUSTOMER).build();
    }

    protected User newMockUser(Long id, String username, String fullName) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");

        return User.builder().id(id).username(username).password(encode).email(fullName + "@nate.com").fullName(fullName).role(Role.CUSTOMER).createData(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
    }

    protected Account newAccount(Long number, User user) {
        return new Account(number, 1234L, user);
    }

    protected Account newMockAccount(Long id, Long number, Long balance, User user) {
        return new Account(id, number, 1234L, balance, user);
    }


}
