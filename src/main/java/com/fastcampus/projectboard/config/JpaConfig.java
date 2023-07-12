package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing // Auditing 활성화
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // todo: spring security 적용 후 현재 로그인한 유저의 이름을 설정하도록 수정하자 그래 잘하자.
        return () -> Optional.of("Dongmin Kim");
    }
}
