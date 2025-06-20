package com.example.Gym.entity;

import com.example.Gym.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER_TB")
@Data
public class UserEntity {

    @Id
    private String id;

    private String pw;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;  // ROLE_USER / ROLE_MANAGER

    private String field;  // 매니저 전용 분야
}
