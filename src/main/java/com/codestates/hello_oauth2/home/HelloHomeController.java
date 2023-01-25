package com.codestates.hello_oauth2.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {  // hello-oauth2 화면에 대한 View를 리턴
    // ----------------------------------------------------------------------
    // 인증된 Authentication 정보 확인
    // -> OAuth 2 인증이 성공적으로 수행되면 SecurityContext에 인증된 Authentication이 저장되는 Spring Security의 특성으로 인증된 Authentication의 사용자 정보 확인
    // ----------------------------------------------------------------------

//    // SecurityContext를 이용하여, OAuth 2 인증된 사용자 정보 얻기
//    @GetMapping("/hello-oauth2")
//    public String home() {
//        // SecurityContext에서 인증된 Authentication 객체를 통해 Principal 객체를 얻는다
//        var oAuth2User = (OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // OAuth2User 객체에 저장되어 있는 사용자 정보 중에서 getAttributes() 메서드를 통해 사용자 이메일 정보를 얻는다
//        System.out.println(oAuth2User.getAttributes().get("email"));
//        return "hello-oauth2";
//    }
//
//    // 인증된 Authentication 객체를 핸들러 메서드 파라미터로 전달받아, OAuth 2 인증된 사용자 정보 얻기
//    @GetMapping("/hello-oauth2")
//    public String home(Authentication authentication) { // 인증된 Authentication 객체를 핸들러 메서드의 파라미터로 전달 받는다
//        var oAuth2User = (OAuth2User)authentication.getPrincipal();
//        System.out.println(oAuth2User);
//        System.out.println("User's email in Google : " + oAuth2User.getAttributes().get("email"));
//        return "hello-oauth2";
//    }
//
//    // OAuth2User를 파라미터로 전달받아, OAuth 2 인증된 사용자 정보 얻기
//    @GetMapping("/")
//    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {  // @AuthenticationPrincipal 애너테이션을 이용해 OAuth2User 객체를 파라미터로 전달 받는다
//        System.out.println("User's email in Google : " + oAuth2User.getAttributes().get("email"));
//        return "hello-oauth2";
//    }

    // ----------------------------------------------------------------------
    // Authorization Server로부터 전달 받은 Access Token 확인
    // -> OAuth 2 인증이 성공적으로 수행되면 내부적으로 리소스 서버에 접근할 때 사용되는 Access Token 정보 확인
    // ----------------------------------------------------------------------

    // OAuth2AuthorizedClientService : 권한을 부여받은 클라이언트(OAuth2AuthorizedClient)를 관리 -> OAuth2AuthorizedClient가 보유하고 있는 Access Token에 접근 가능
    private final OAuth2AuthorizedClientService authorizedClientService;

    // OAuth2AuthorizedClientService DI 받는다
    public HelloHomeController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    // OAuth2AuthorizedClientService를 DI 받아, OAuth 2 인증 후 Resource Server에 접근할 때 사용되는 Access Token 얻기
    // -> 하나 이상의 핸들러 메서드에서 OAuth2AuthorizedClient를 사용해야한다면 권장하는 방법
    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        // loadAuthorizedClient() 호출 -> OAuth2AuthorizedClientRepository에 저장되어있는 OAuth2AuthorizedClient 조회
        var authorizedClient = authorizedClientService.loadAuthorizedClient("google", authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();  // OAuth2AccessToken 객체 얻기
        System.out.println("Access Token Value : " + accessToken.getTokenValue());  // Access Token 문자열 출력
        System.out.println("Access Token Type : " + accessToken.getTokenType().getValue()); // Token 타입 출력
        System.out.println("Access Token Scopes : " + accessToken.getScopes()); // Token으로 접근 가능한 리소스 범위 출력
        System.out.println("Access Token Issued At : " + accessToken.getIssuedAt());    // Token 발행일시 출력
        System.out.println("Access Expires At : " + accessToken.getExpiresAt());    // Token 만료일시 출력

        return "hello-oauth2";
    }

    // OAuth2AuthorizedClient를 핸들러 메서드의 파라미터로 전달받아, OAuth 2 인증 후 Resource Server에 접근할 때 사용되는 Access Token 얻기
    // @RegisteredOAuth2AuthorizedClient -> OAuth2AuthorizedClientRepository에 저장되어있는 OAuth2AuthorizedClient를 파라미터로 받아서 Access Token 객체 얻기
//    @GetMapping("/hello-oauth2")
//    public String home(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//        System.out.println("Access Token Value : " + accessToken.getTokenValue());  // Access Token 문자열 출력
//        System.out.println("Access Token Type : " + accessToken.getTokenType().getValue()); // Token 타입 출력
//        System.out.println("Access Token Scopes : " + accessToken.getScopes()); // Token으로 접근 가능한 리소스 범위 출력
//        System.out.println("Access Token Issued At : " + accessToken.getIssuedAt());    // Token 발행일시 출력
//        System.out.println("Access Expires At : " + accessToken.getExpiresAt());    // Token 만료일시 출력
//
//        return "hello-oauth2";
//    }
}
