package com.example.bank.dto;

import com.example.bank.CustomDateUtil;
import com.example.bank.jwt.LoginUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {

    private Long id;
    private String username;
    private String createDate;

    public LoginResponse(LoginUser loginUser) {
        this.id = loginUser.getUser().getId();
        this.username = loginUser.getUser().getUsername();
        this.createDate = CustomDateUtil.toStringFormat(loginUser.getUser().getCreateDate());
    }
}
