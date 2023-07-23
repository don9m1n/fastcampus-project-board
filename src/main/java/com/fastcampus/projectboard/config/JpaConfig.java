package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

@EnableJpaAuditing // Auditing 활성화
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // SecurityContextHolder: 시큐리티 인증 정보를 가지고 있는 클래스
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(securityContext -> securityContext.getAuthentication())
                .filter(authentication -> authentication.isAuthenticated()) // 인증 정보를 꺼내기 전에 인증 검사
                .map(authentication -> authentication.getPrincipal())
                .map(BoardPrincipal.class::cast)
                .map(boardPrincipal -> boardPrincipal.getUsername());

    }
}
