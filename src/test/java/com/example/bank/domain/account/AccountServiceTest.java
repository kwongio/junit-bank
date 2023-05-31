package com.example.bank.domain.account;

import com.example.bank.config.dummy.DummyObject;
import com.example.bank.domain.user.User;
import com.example.bank.domain.user.UserRepository;
import com.example.bank.dto.AccountSaveRequest;
import com.example.bank.dto.AccountSaveResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class AccountServiceTest extends DummyObject {


    @InjectMocks
    private AccountService accountService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private ObjectMapper objectMapper;


    @Test
    public void createAccount() throws IOException {
        Long userId = 1L;
        AccountSaveRequest accountSaveRequest = new AccountSaveRequest(1234L, 1234L);

        User ssar = newMockUser(userId, "ssar", "쌀");
        when(userRepository.findById(any())).thenReturn(null);


        when(accountRepository.findByNumber(any())).thenReturn(Optional.empty());


        Account account = newMockAccount(1L, 1234L, 1000L, ssar);
        when(accountRepository.save(any())).thenReturn(account);


        AccountSaveResponse account1 = accountService.createAccount(userId, accountSaveRequest);



    }

}