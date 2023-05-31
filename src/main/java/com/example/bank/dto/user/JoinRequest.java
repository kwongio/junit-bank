package com.example.bank.dto.user;

import com.example.bank.domain.user.Role;
import com.example.bank.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@AllArgsConstructor
public class JoinRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문, 숫자만 입력 가능합니다.")
    private String username;

    @Size(min = 4, max = 20)
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank
    private String fullName;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder().username(username).password(passwordEncoder.encode(password)).email(email).fullName(fullName).role(Role.CUSTOMER).build();

    }
}
