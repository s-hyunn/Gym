package com.example.Gym.controller;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.enums.Role;
import com.example.Gym.service.UserService;
import com.example.Gym.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JWTUtil jwtUtil; // ✅ JWT 유틸 주입

    @PostMapping("/login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("pw") String pw,
                        HttpServletRequest request,
                        Model model) {

        boolean success = userService.login(id, pw);

        if (success) {
            UserEntity user = userService.getUser(id);

            // ✅ 세션에 유저 저장
            request.getSession().setAttribute("loginUser", user);

            // ✅ JWT 토큰 생성 및 저장 (원하면 response header 또는 cookie에도 넣을 수 있음)
            String token = jwtUtil.createToken(user.getId(), user.getRole().name());
            request.getSession().setAttribute("jwt", token); // 임시 저장

            // ✅ 역할별 분기
            if (user.getRole() == Role.ROLE_MANAGER) {
                return "redirect:/qna/manager/view";
            } else {

                return "redirect:/qna/user/view"; // 일반 회원 메인 페이지

            }

        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login"; // 로그인 실패 시 다시 로그인 페이지
        }
    }

}
