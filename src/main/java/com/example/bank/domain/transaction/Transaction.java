package com.example.bank.domain.transaction;


import com.example.bank.domain.account.Account;
import com.example.bank.domain.user.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "transaction_tb")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account withdrawAccount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account depositAccount;

    private Long amount;

    private Long withdrawAccountBalance;
    private Long depositAccountBalance;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TranscationEnum gubun;

    private String sender;
    private String receiver;
    private String tel;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createData;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;


    @Builder
    public Transaction(Long id, Account withdrawAccount, Account depositAccount, Long amount, Long withdrawAccountBalance, Long depositAccountBalance, LocalDateTime createData, LocalDateTime updateDate, TranscationEnum gubun) {
        this.id = id;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.createData = createData;
        this.updateDate = updateDate;
        this.gubun = gubun;
    }
}
