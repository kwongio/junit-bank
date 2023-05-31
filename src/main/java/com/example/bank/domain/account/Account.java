package com.example.bank.domain.account;

import com.example.bank.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "account_tb")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 4)
    private Long number;

    @Column(nullable = false, length = 4)
    private Long password;

    @Column(nullable = false)
    private Long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Account(Long number, Long password, User user) {
        this.number = number;
        this.password = password;
        this.balance = 1000L;
        this.user = user;
    }

    public Account(Long id, Long number, Long password, Long balance, User user) {
        this.id = id;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.user = user;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
}
