package com.example.Gym.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("id") String id,
                                        @RequestParam("pw") String pw) {
        boolean success = userService.login(id, pw);
        if (success) {
            UserEntity user = userService.getUser(id);
            return ResponseEntity.ok("로그인 성공: " + user.getName() + " (" + user.getRole() + ")");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호 확인");
        }
    }

}
