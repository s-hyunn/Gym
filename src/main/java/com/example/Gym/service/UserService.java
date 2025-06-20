package com.example.Gym.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Gym.entity.UserEntity;
import com.example.Gym.enums.Role;
import com.example.Gym.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(UserEntity user) {
        userRepository.save(user);
    }

    public List<UserEntity> getAllManagers() {
        return userRepository.findByRole(Role.ROLE_MANAGER);
    }
    
    public boolean login(String id, String pw) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            // 단순 비교 (비밀번호 암호화 전용)
            return user.getPw().equals(pw);
        }
        return false;
    }

    public UserEntity getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }
    

    public boolean isDuplicateId(String id) {
        return userRepository.existsById(id);
    }
}