package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// @EnableWebSecurity 생략해도 됨. 스프링 부트와 연동해서 사용하는 경우 자동으로 설정됨.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .isCustomLoginPage() // 커스텀 로그인 페이지가 없으면 기본 로그인 화면으로 ㄱㄱ
                )
                .build();
    }
}
