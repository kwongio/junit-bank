package com.example.bank.dto;

import com.example.bank.domain.account.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountSaveResponse {
    private final Long id;
    private final Long number;
    private final Long balance;

    public AccountSaveResponse(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.balance = account.getBalance();
    }
}
