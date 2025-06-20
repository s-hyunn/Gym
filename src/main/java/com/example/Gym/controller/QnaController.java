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
    public String answerQna(@RequestParam("qno") Long qno,
                            @RequestParam("answer") String answer) {
        qnaService.answer(qno, answer);
        return "redirect:/qna/manager/view";
    }

    @PostMapping("/qna/deleteAnswer")
    public String deleteAnswer(@RequestParam("qno") Long qno) {
        qnaService.deleteAnswer(qno);
        return "redirect:/qna/manager/view";
    }

    @GetMapping("/qna/manager/view")
    public String showManagerQnaPage(HttpServletRequest request,
                                     @RequestParam(value = "status", required = false) String status,
                                     Model model) {

        UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        String field = loginUser.getField();
        List<QnaEntity> qnaList;

        if (status != null && !status.isEmpty()) {
            qnaList = qnaService.getFilteredInquiries(status, field);
        } else {
            qnaList = qnaService.getManagerInquiries(field);
        }

        model.addAttribute("qnaList", qnaList);
        model.addAttribute("status", status);
        return "manager_qna";
    }
}
