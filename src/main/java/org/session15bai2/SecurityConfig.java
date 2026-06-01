package org.session15bai2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // Disable CSRF cho REST API
                .csrf(csrf -> csrf.disable())

                // Không dùng session
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Phân quyền API
                .authorizeHttpRequests(auth -> auth

                        // Public API
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register"
                        ).permitAll()

                        // Các API khác phải login
                        .requestMatchers("/api/**").authenticated()

                        // Request khác cho phép
                        .anyRequest().permitAll()
                )

                // Disable form login mặc định
                .formLogin(form -> form.disable())

                // Disable logout mặc định
                .logout(logout -> logout.disable())

                // Basic auth để test Postman
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
