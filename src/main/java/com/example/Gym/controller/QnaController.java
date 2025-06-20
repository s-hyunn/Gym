package com.example.Gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String createInquiry(@RequestParam("userId") String userId,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam("field") String field) {
        qnaService.createInquiry(userId, title, content, field);
        return "redirect:/qna/user/view";
    }

    @GetMapping("/qna/user")
    @ResponseBody
    public List<QnaEntity> getUserQna(@RequestParam("id") String id) {
        return qnaService.getUserInquiries(id);
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

        if (status == null || status.isEmpty() || status.equals("전체")) {
            // ✅ 전체 보기
            qnaList = qnaService.getAllInquiriesByField(field);
        } else {
            // ✅ 상태 필터 적용
            qnaList = qnaService.getFilteredInquiries(status, field);
        }

        model.addAttribute("qnaList", qnaList);
        model.addAttribute("status", status);
        return "manager_qna";
    }

    
    @GetMapping("/qna/user/view")
    public String showUserQnaPage(HttpServletRequest request, Model model) {
    	UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login"; // 비로그인 상태면 로그인 페이지로
        }
        String id = loginUser.getId();
        List<QnaEntity> qnaList = qnaService.getUserInquiries(id);
        model.addAttribute("qnaList", qnaList);

        return "myPage"; // 타임리프 뷰 반환
    }
}

