package com.example.bank.domain.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("관리자"), CUSTOMER("고객");

    private final String value;

}
