package com.example.bank.domain.user;


import com.example.bank.dto.user.JoinRequest;
import com.example.bank.dto.user.JoinResponse;
import com.example.bank.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public JoinResponse join(JoinRequest joinRequest) {
        boolean existUser = userRepository.existsByUsername(joinRequest.getUsername());
        if (existUser) {
            throw new CustomApiException("동일한 username이 존재합니다.");
        }
        User userPS = joinRequest.toEntity(passwordEncoder);
        userRepository.save(userPS);
        return new JoinResponse(userPS);
    }
}
