package com.codestates.section2week4;

import com.codestates.section2week4.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanTest {    // @ComponentScan 형식의 DependencyConfig 작동 확인 테스트 코드
    public static void main(String[] args) {
        ApplicationContext ac= new AnnotationConfigApplicationContext(DependencyConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        System.out.println(MemberService.class.isInstance(memberService));
    }
}
