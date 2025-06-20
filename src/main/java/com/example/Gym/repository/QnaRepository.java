package com.example.Gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gym.entity.QnaEntity;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {
    List<QnaEntity> findByUser_Id(String userId);  // 마이페이지용
    List<QnaEntity> findByStatusAndField(String status, String field);  // 매니저 분야 + 상태 필터
    List<QnaEntity> findByField(String field);  // 전체 상태 조회 시
}
