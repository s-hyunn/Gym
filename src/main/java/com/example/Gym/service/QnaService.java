package com.example.Gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Gym.entity.QnaEntity;
import com.example.Gym.entity.UserEntity;
import com.example.Gym.repository.QnaRepository;
import com.example.Gym.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    // 문의 작성
    public void createInquiry(String userId, String title, String content, String field) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));
        QnaEntity qna = new QnaEntity();
        qna.setUser(user);
        qna.setTitle(title);
        qna.setContent(content);
        qna.setStatus("미답변");  // 통일된 상태값
        qna.setField(field);
        qnaRepository.save(qna);
    }

    public List<QnaEntity> getUserInquiries(String userId) {
        return qnaRepository.findByUser_Id(userId);
    }

    public List<QnaEntity> getManagerInquiries(String field) {
        return qnaRepository.findByStatusAndField("미답변", field);
    }

    public List<QnaEntity> getFilteredInquiries(String status, String field) {
        return qnaRepository.findByStatusAndField(status, field);
    }

    public void answer(Long qno, String answer) {
        QnaEntity qna = qnaRepository.findById(qno).orElseThrow();
        qna.setAnswer(answer);
        qna.setStatus("답변완료");
        qnaRepository.save(qna);
    }

    public void deleteAnswer(Long qno) {
        QnaEntity qna = qnaRepository.findById(qno).orElseThrow();
        qna.setAnswer(null);
        qna.setStatus("미답변");
        qnaRepository.save(qna);
    }
}
	