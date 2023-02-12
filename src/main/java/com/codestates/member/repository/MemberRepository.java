package com.codestates.member.repository;

import com.codestates.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// <Member 엔티티 클래스, Member 엔티티 클래스의 @Id가 붙은 변수 타입>
public interface MemberRepository extends CrudRepository<Member, Long> {
    // Spring Data JDBC에서 지원하는 쿼리 메서드 정의를 이용한 데이터 조회 메서드 정의
    // 쿼리 메서드 정의 -> find + By + SQL 쿼리문에서 WHERE절의 컬렴명 + (WHERE절 컬럼의 조건이 되는 데이터)
    Optional<Member> findByEmail(String email);
}
