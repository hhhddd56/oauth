package com.oauth.oauthserver.config;

import com.oauth.oauthserver.entity.User;
import com.oauth.oauthserver.repository.UserRepository;
import com.oauth.oauthserver.service.CustomAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests(authorizeRequests ->
                    authorizeRequests.anyRequest().authenticated()
                )
            .formLogin(Customizer.withDefaults());
        // @formatter:on

        return http.build();
    }

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider);
    }


    // 수동으로 넣는 부분이라 추후 삭제
    @Autowired
    UserRepository userRepository;
    @Bean
    public UserDetailsService users() {
        User user = new User();
        user.setClientId("admin");
        user.setId(1L);
        user.setClientSecret("$2a$10$6CW1agMzVzBhxDzK0PcxrO/cQcmN9h8ZriVEPy.6DJbVeyATG5mWe"); //admin
        user.setRole("admin");

        userRepository.save(user);
        return null;
    }
}
