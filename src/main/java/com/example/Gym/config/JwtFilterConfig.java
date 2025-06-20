package com.example.Gym.config;

import com.example.Gym.filter.JwtAuthFilter;
import com.example.Gym.util.JWTUtil;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterConfig {

    private final JWTUtil jwtUtil;

    public JwtFilterConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthFilter(jwtUtil));
        registrationBean.addUrlPatterns("/qna/*", "/mypage/*"); // 인증 필요 URL만 지정
        return registrationBean;
    }
}
