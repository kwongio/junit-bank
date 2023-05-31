package com.example.bank.domain.user;


import com.example.bank.dto.ResponseDto;
import com.example.bank.dto.user.JoinRequest;
import com.example.bank.dto.user.JoinResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody JoinRequest joinRequest) {
        JoinResponse join = userService.join(joinRequest);
        return ResponseEntity.ok(new ResponseDto<>(1, "회원가입 성공", join));
    }
}
