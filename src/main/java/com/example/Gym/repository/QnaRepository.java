package com.example.Gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gym.entity.QnaEntity;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {
    List<QnaEntity> findByUser_Id(String userId); // 마이페이지
    List<QnaEntity> findByStatusAndUser_Field(String status, String field); // 매니저 받은 문의
}