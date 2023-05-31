package com.example.bank.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Table(name = "user_tb")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 20)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','CUSTOMER')", nullable = false)
    private Role role = Role.ADMIN;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;


    @Builder
    public User(Long id, String username, String password, String email, String fullName, Role role, LocalDateTime createData, LocalDateTime updateDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.createDate = createData;
        this.updateDate = updateDate;
    }
}
