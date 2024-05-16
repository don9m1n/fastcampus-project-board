package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.common.config.SecurityConfig;
import com.fastcampus.projectboard.domain.user.model.UserAccount;
import com.fastcampus.projectboard.domain.user.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(
                Optional.of(UserAccount.of(
                "test123",
                "1234",
                "test@email.com",
                "test1234",
                "test-memo"
        )));

    }
}
