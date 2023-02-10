package com.codestates.section2week4.member;

import com.codestates.section2week4.DependencyConfig;

public class MemberTest {   // 회원 로직 테스트 구현
    public static void main(String[] args) {
        DependencyConfig dependencyConfig = new DependencyConfig();
        MemberService memberService = dependencyConfig.memberService();

        Member member = new Member(0L, "lucky@codestates.com", "KimLucky", "010-0000-0000");
        memberService.createMember(member);

        Member currentMember = memberService.getMember(0L);

        System.out.println("회원 가입한 유저 : " + member.getName());
        System.out.println("현재 첫번째 유저 : " + currentMember.getName());

        if (member.getName().equals(currentMember.getName())) {
            System.out.println("새롭게 가입한 사용자와 현재 사용자가 같습니다.");
        }

        memberService.deleteMember(0L); // 회원 삭제

        if (memberService.getMember(0L) == null) {
            System.out.println("회원 삭제가 정상적으로 완료되었습니다.");
        }
    }
}
