package com.example.Gym.controller;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.enums.Role;
import com.example.Gym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 처리
    @PostMapping("/user/register")
    public String registerUser(@RequestParam("id") String id,
                               @RequestParam("pw") String pw,
                               @RequestParam("name") String name,
                               @RequestParam("role") String role,
                               @RequestParam(name = "field", required = false) String field) {
        if (userService.isDuplicateId(id)) {
            return "redirect:/errorPage"; // 실패 시 에러 페이지로
        }

        UserEntity user = new UserEntity();
        user.setId(id);
        user.setPw(pw);
        user.setName(name);
        user.setRole(Role.valueOf(role));
        user.setField(field);

        userService.register(user);
        return "redirect:/successPage"; // 리디렉트로 /successPage URL 요청
    }

    // 성공 페이지 매핑 (리디렉트 후 이 메서드로 연결됨)
    @GetMapping("/successPage")
    public String successPage() {
        return "successPage"; // /templates/successPage.html
    }

}
