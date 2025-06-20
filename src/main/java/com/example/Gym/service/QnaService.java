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

    public void createInquiry(String userId, String title, String content) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        QnaEntity qna = new QnaEntity();
        qna.setUser(user);
        qna.setTitle(title);
        qna.setContent(content);
        qna.setStatus("대기");
        qnaRepository.save(qna);
    }

    public List<QnaEntity> getUserInquiries(String userId) {
        return qnaRepository.findByUser_Id(userId);
    }

    public List<QnaEntity> getManagerInquiries(String field) {
        return qnaRepository.findByStatusAndUser_Field("대기", field);
    }

    public void answer(Long qno, String answer) {
        QnaEntity qna = qnaRepository.findById(qno).orElseThrow();
        qna.setAnswer(answer);
        qna.setStatus("완료");
        qnaRepository.save(qna);
    }
}
