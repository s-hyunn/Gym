package com.example.Gym.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.service.UserService;
import com.example.Gym.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JWTUtil jwtUtil; // ✅ JWT 유틸 주입

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("id") String id,
                                        @RequestParam("pw") String pw) {
        boolean success = userService.login(id, pw);
        if (success) {
            UserEntity user = userService.getUser(id);

            // JWT 토큰 생성
            String token = jwtUtil.createToken(user.getId(), user.getRole().name());

            return ResponseEntity.ok("로그인 성공 ✅\nAccess Token: " + token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("❌ 로그인 실패: 아이디 또는 비밀번호 확인");
        }
    }
}
