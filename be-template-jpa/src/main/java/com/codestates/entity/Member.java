package com.codestates.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USERS") // 엔티티 클래스와 테이블 매핑, 테이블 이름 설정
@Table(name = "USERS")  // 테이블 이름 설정, optional
@Getter
@Setter
@NoArgsConstructor  // 파라미터가 없는 기본 생성자, 미추가 시 Spring Data JPA에서는 잦은 오류 발생
public class Member {
    @Id // 테이블 기본키 할당
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이블 IDENTITY 기본키 생성
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 테이블 SEQUENCE 기본키 생성
//    @GeneratedValue(strategy = GenerationType.AUTO)   // 테이블 AUTO(적절한) 기본키 생성
    private Long memberId;

    // nullable : 컬럼 null 값 허용 여부, updatable : 컬럼 데이터 수정 가능 여부, unique : 하나의 컬럼에 유니크 제약 조건 설정
    // nullable의 기본값은 false(기본값)로 설정 시, SQL문에서 NOT NULL 설정이 불가하다
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 생성 시간

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now(); // 수정 시간

    @Transient  // 테이블 컬럼과 매핑(저장, 조회)하지 않는다, 임시 메모리 용도
    private String age;

    @OneToMany(mappedBy = "member") // 1:N -> 1에게 설정 -> 양방향 관계 완성, mappedBy : 외래키
    private List<Order> orders = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
