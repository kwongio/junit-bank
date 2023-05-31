package com.example.bank.domain.account;


import com.example.bank.dto.AccountSaveRequest;
import com.example.bank.dto.AccountSaveResponse;
import com.example.bank.jwt.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountSaveRequest accountSaveRequest, @AuthenticationPrincipal LoginUser user) {
        AccountSaveResponse accountSaveResponse = accountService.createAccount(user.getUser().getId(), accountSaveRequest);
        return ResponseEntity.ok(accountSaveResponse);
    }
}
