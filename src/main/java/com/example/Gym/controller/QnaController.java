package com.example.Gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Gym.entity.QnaEntity;
import com.example.Gym.entity.UserEntity;
import com.example.Gym.service.QnaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    @PostMapping("/qna")
    @ResponseBody
    public ResponseEntity<?> createInquiry(@RequestParam("userId") String userId,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam("field") String field) { 
        qnaService.createInquiry(userId, title, content, field);
        return ResponseEntity.ok("문의 등록 완료");
    }


    @GetMapping("/qna/user")
    @ResponseBody
    public List<QnaEntity> getUserQna(@RequestParam("userId") String userId) {
        return qnaService.getUserInquiries(userId);
    }

    @GetMapping("/qna/manager")
    @ResponseBody
    public List<QnaEntity> getManagerQna(@RequestParam("field") String field) {
        return qnaService.getManagerInquiries(field);
    }

    @PostMapping("/qna/answer")
    @ResponseBody
    public ResponseEntity<?> answerQna(@RequestParam("qno") Long qno,
                                       @RequestParam("answer") String answer) {
        qnaService.answer(qno, answer);
        return ResponseEntity.ok("답변 완료");
    }

    @GetMapping("/qna/manager/view")
    public String showManagerQnaPage(HttpServletRequest request, Model model) {
        UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login"; // 비로그인 상태면 로그인 페이지로
        }

        String id = loginUser.getId();
        List<QnaEntity> qnaList = qnaService.getManagerInquiries(id);
        model.addAttribute("qnaList", qnaList);

        return "manager_qna"; // 타임리프 뷰 반환
    }
}
