package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * V2
 *  - 메서드 구현
 *  - DI 적용
 */
@Service
public class MemberService {
    private MemberRepository memberRepository;

    // MemberRepository DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 검증
        verifyExistsEmail(member.getEmail());

        // 회원 정보 저장
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // 이미 존재하는 회원인지 검증
        Member findMember = findVerifiedMember(member.getMemberId());

        // 이름 정보와 전화번호 정보 업데이트
        // null 값 허용을 위해 Optional.of()가 아닌, Optional.ofNullable() 사용 -> NullPointException 미발생
        Optional.ofNullable(member.getName()).ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone()).ifPresent(phone -> findMember.setPhone(phone));

        // 회원 정보 업데이트
        // Spring Data JDBC에서는 @Id가 붙은 엔티티 클래스의 멤버 변수 값이 0 또는 null 값이면 신규 데이터로 판단되어, 테이블에 insert 쿼리를 전송한다
        // 하지만 0 또는 null 값이 아니라면 기존 데이터로 판단되어, 테이블에 update 쿼리를 전송한다
        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {
        // 특정 회원 정보 조회
        return findVerifiedMember(memberId);
    }

    public List<Member> findMembers() {
        // 모든 회원 정보 조회
        return (List<Member>) memberRepository.findAll();   // findAll() 메서드의 리턴값이 Iterable<T>이기 때문에 List<Member>로 캐스팅
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        // 특정 회원 정보 삭제
        memberRepository.delete(findMember);
    }

    // 이미 등록된 이메일 주소인지 검증
    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    // 이미 존재하는 회원인지 검증
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        // orElseThrow() : optionalMember 객체가 null 값이 아니라면 해당 객체를 리턴하고, null 값이라면 예외를 던진다
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }
}
