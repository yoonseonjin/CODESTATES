package com.codestates.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {    // 원하는 인증 방식과 웹 페이지에 대한 접근 권한 설정
    @Value("${spring.security.oauth2.client.registration.google.clientId}")  // application.yml에서 설정한 클라이언트 ID 로드
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.clientSecret}")   // application.yml에서 설정한 클라이언트 보안 비밀번호 로드
    private String clientSecret;

    // Spring Boot 자동 구성으로 OAuth 2 인증 설정 -> application.yml에서 설정된 클라이언트 ID, 보안 비밀번호 정보를 기반으로 ClientRegistrationRepository Bean 생성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HttpSecurity를 통한 HTTP Request에 대한 보안 설정 구성
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()) // 인증된 request에 대해서만 접근 허용
                .oauth2Login(withDefaults());   // OAuth 2 로그인 인증 활성화
        return http.build();
    }

    // Configuration을 통해 Bean을 등록함으로 OAuth 2 인증 설정 -> ClientRegistrationRepository Bean을 직접 등록
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {    // ClientRegistration을 저장하는 Repository
        // private 메서드인 clientRegistration()을 호출해서 ClientRegistration 인스턴스를 리턴 받는다
        var clientRegistration = clientRegistration();

        // ClientRegistrationRepository 인터페이스의 구현 클래스인 InMemoryClientRegistrationRepository의 인스턴스 생성
        // -> InMemoryClientRegistrationRepository는 ClientRegistration을 메모리에 저장
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    // private 메서드인 ClientRegistration 인스턴스를 생성 -> clientRegistration은 GOOGLE API 콘솔에 등록했던 OAuth 2 클라이언트 ID, 보안 비밀번호에 대한 정보를 표현
    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId(clientId).clientSecret(clientSecret).build();
    }
}
