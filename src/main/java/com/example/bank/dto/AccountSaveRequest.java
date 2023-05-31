package com.example.bank.dto;

import com.example.bank.domain.account.Account;
import com.example.bank.domain.user.User;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountSaveRequest {
    @NotNull
    @Digits(integer = 4, fraction = 0)
    private final Long number;

    @NotNull
    @Digits(integer = 4, fraction = 0)
    private final Long password;

    public Account toEntity(User user) {
        return new Account(number, password, user);
    }
}