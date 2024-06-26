package com.fastcampus.projectboard.common.config;

import com.fastcampus.projectboard.common.dto.BoardPrincipal;
import com.fastcampus.projectboard.common.dto.KakaoOAuth2Response;
import com.fastcampus.projectboard.domain.user.dto.UserAccountDto;
import com.fastcampus.projectboard.domain.user.repository.UserAccountRepository;
import com.fastcampus.projectboard.domain.user.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity // 생략 가능
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/articles", "/articles/search-hashtag").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)
                        )
                )
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
        log.debug("loadByUsername start??");
        return username -> userAccountService.searchUser(username)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. - username: " + username));
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
            UserAccountService userAccountService,
            PasswordEncoder passwordEncoder
    ) {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return userRequest -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
            String registrationId = userRequest.getClientRegistration().getRegistrationId(); // kakao
            String providerId = String.valueOf(kakaoResponse.getId());
            String username = registrationId + "_" + providerId;
            String dummyPassword = passwordEncoder.encode("{bcrypt}dummy");

            return userAccountService.searchUser(username)
                    .map(BoardPrincipal::from)
                    .orElseGet(() ->
                            BoardPrincipal.from(
                                    userAccountService.saveUser(
                                            username,
                                            dummyPassword,
                                            kakaoResponse.email(),
                                            kakaoResponse.nickname(),
                                            null
                                    )
                            )
                    );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
