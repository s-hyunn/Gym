package com.example.Gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Gym.service.QnaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    @PostMapping("/qna")
    public String createInquiry(@RequestParam("userId") String userId,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content) {
        qnaService.createInquiry(userId, title, content);
        return "redirect:/myPage";
    }

    @PostMapping("/qna/answer")
    public String answerQna(@RequestParam("qno") Long qno,
                                       @RequestParam("answer") String answer) {
        qnaService.answer(qno, answer);
        return "redirect:/manager";
    }
}
