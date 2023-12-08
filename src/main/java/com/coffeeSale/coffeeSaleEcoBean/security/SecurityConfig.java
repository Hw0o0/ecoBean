package com.coffeeSale.coffeeSaleEcoBean.security;

import com.coffeeSale.coffeeSaleEcoBean.common.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsImplService userDetailsImplService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers( "/user/**").permitAll()
                        .anyRequest().authenticated()   // 어떠한 요청이라도 인증필요
                )
                .exceptionHandling((exceptionConfig) -> exceptionConfig.authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(accessDeniedHandler))//401,403 관련 예외처리
                .userDetailsService(userDetailsImplService);
        return http.build();
    }


    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden...");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyRole('USER')")
    public @interface UserAuthorize {
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public @interface AdminAuthorize {
    }
}
