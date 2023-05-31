package com.example.bank.domain.account;

import com.example.bank.domain.user.User;
import com.example.bank.domain.user.UserRepository;
import com.example.bank.dto.AccountSaveRequest;
import com.example.bank.dto.AccountSaveResponse;
import com.example.bank.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    @Transactional
    public AccountSaveResponse createAccount(Long userId, AccountSaveRequest accountSaveRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다."));

        Optional<Account> accountOP = accountRepository.findByNumber(accountSaveRequest.getNumber());
        if (accountOP.isPresent()) {
            throw new CustomApiException("이미 존재하는 계좌입니다.");
        }

        Account account = accountRepository.save(accountSaveRequest.toEntity(user));
        return new AccountSaveResponse(account);


    }




}
