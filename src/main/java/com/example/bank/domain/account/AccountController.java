package com.example.bank.domain.account;


import com.example.bank.dto.AccountSaveRequest;
import com.example.bank.dto.AccountSaveResponse;
import com.example.bank.jwt.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/s/account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountSaveRequest accountSaveRequest, @AuthenticationPrincipal LoginUser user) {
        AccountSaveResponse accountSaveResponse = accountService.createAccount(user.getUser().getId(), accountSaveRequest);
        return ResponseEntity.ok(accountSaveResponse);
    }

    @GetMapping("/s/account")
    public ResponseEntity<?> getAccountList(@AuthenticationPrincipal LoginUser user) {
        AccountService.AccountListResponse account = accountService.계좌목록보기_유저별(user.getUser().getId());
        return ResponseEntity.ok(account);
    }


    @DeleteMapping("/s/account/{number}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long number, @AuthenticationPrincipal LoginUser loginUser) {
        accountService.deleteAccount(number, loginUser.getUser().getId());
        return ResponseEntity.ok("계좌 삭제 완료");
    }
}