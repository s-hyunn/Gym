package com.example.Gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.enums.Role;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findByRole(Role role); // 매니저 목록용
}