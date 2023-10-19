package com.codestates.section2week4;

import com.codestates.section2week4.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {    // 자동 주입 대상 옵션 처리 방법 테스트 코드
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false)    // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출되지 않는다
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {  // @Nullable : 자동 주입할 대상이 없으면 null이 입력된다
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {  // Optional<> : 자동 주입할 대상이 없으면 Optional.empty가 입력된다
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
