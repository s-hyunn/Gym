package com.example.Gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gym.entity.QnaEntity;
import com.example.Gym.service.QnaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    @PostMapping("/qna")
    public ResponseEntity<?> createInquiry(@RequestParam("userId") String userId,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam("field") String field) { 
        qnaService.createInquiry(userId, title, content, field);
        return ResponseEntity.ok("문의 등록 완료");
    }


    @GetMapping("/qna/user")
    public List<QnaEntity> getUserQna(@RequestParam("userId") String userId) {
        return qnaService.getUserInquiries(userId);
    }

    @GetMapping("/qna/manager")
    public List<QnaEntity> getManagerQna(@RequestParam("field") String field) {
        return qnaService.getManagerInquiries(field);
    }

    @PostMapping("/qna/answer")
    public ResponseEntity<?> answerQna(@RequestParam("qno") Long qno,
                                       @RequestParam("answer") String answer) {
        qnaService.answer(qno, answer);
        return ResponseEntity.ok("답변 완료");
    }
}
