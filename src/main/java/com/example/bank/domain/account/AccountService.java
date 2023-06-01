package com.example.bank.domain.account;

import com.example.bank.domain.user.User;
import com.example.bank.domain.user.UserRepository;
import com.example.bank.dto.AccountSaveRequest;
import com.example.bank.dto.AccountSaveResponse;
import com.example.bank.ex.CustomApiException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountListResponse 계좌목록보기_유저별(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다."));
        List<Account> accounts = accountRepository.findByUserId(user.getId());
        return new AccountListResponse(user, accounts);


    }

    @Data
    public static class AccountListResponse {
        private String fullName;
        private List<AccountDto> accountDtos = new ArrayList<>();

        public AccountListResponse(User user, List<Account> accounts) {
            this.fullName = user.getFullName();
            this.accountDtos = accounts.stream().map(AccountDto::new).toList();
        }

        @Data
        public class AccountDto {
            private Long id;
            private Long number;
            private Long balance;

            public AccountDto(Account account) {
                this.id = account.getId();
                this.number = account.getNumber();
                this.balance = account.getBalance();
            }
        }
    }

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


    @Transactional
    public void deleteAccount(Long number, Long userId) {
        Account account = accountRepository.findByNumber(number).orElseThrow(() -> new CustomApiException("계좌를 찾을 수 없습니다."));
        if (!account.getUser().getId().equals(userId)) {
            throw new CustomApiException("계좌를 삭제할 수 없습니다.");
        }
        accountRepository.delete(account);
    }


}
