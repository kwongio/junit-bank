package com.example.bank.domain.user;

import com.example.bank.config.dummy.DummyObject;
import com.example.bank.dto.user.JoinRequest;
import com.example.bank.dto.user.JoinResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest  extends DummyObject {

    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository useRepository;
    @Spy
    private BCryptPasswordEncoder passwordEncoder;


    @Test
    public void 회원가입_test() throws IOException {
        JoinRequest joinRequest = new JoinRequest("username", "password", "email", "fullName");
        when(useRepository.findByUsername(any())).thenReturn(Optional.empty());


        when(useRepository.save(any())).thenReturn(newUser("username", "fullName"));
        JoinResponse response = userService.join(joinRequest);
        System.out.println("테스트: " + response);

    }

}