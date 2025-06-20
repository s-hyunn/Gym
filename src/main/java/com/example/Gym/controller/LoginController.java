package com.example.Gym.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.enums.Role;
import com.example.Gym.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("pw") String pw,
                        HttpServletRequest request,
                        Model model) {
        boolean success = userService.login(id, pw);

        if (success) {
            UserEntity user = userService.getUser(id);

            request.getSession().setAttribute("loginUser", user);

            // 역할에 따라 리다이렉트
            if (user.getRole() == Role.ROLE_MANAGER) {
                return "redirect:/qna/manager/view";
            } else {
                return "redirect:/qna/user/view"; // 일반 회원 메인 페이지
            }
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login"; // 다시 로그인 페이지로
        }
    }
    
}
