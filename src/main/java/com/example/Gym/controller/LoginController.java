package com.example.Gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("id") String id,
                                        @RequestParam("pw") String pw) {
        boolean success = userService.login(id, pw);
        if (success) {
            UserEntity user = userService.getUser(id);
            if(user.getRole().toString().equals("ROLE_MANAGER")) {
            	System.out.println(user.getRole());
            	return "manager";
            }
            else {
            	System.out.println(user.getRole());
            	return "myPage";
            }
        }
        else {
        	return "redirect:/login";
        }
    }
    
    @GetMapping("/manager")
    public String manager() {
    	return "manager";
    }
    @GetMapping("/myPage")
    public String myPage() {
    	return "myPage";
    }

}
