package com.example.bank.dto.user;

import com.example.bank.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class JoinResponse {
    private Long id;
    private String username;
    private String fullName;

    public JoinResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
    }

}