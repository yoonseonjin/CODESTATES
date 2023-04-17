package com.codestates.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 *  - 멤버 변수 추가
 *  - lombok 추가
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id // Spring Data JDBC 엔티티 객체의 식별자로 사용할 필드에 적용 -> Member 클래스와 MEMBER 테이블을 매핑
    private long memberId;

    private String email;

    private String name;

    private String phone;
}
