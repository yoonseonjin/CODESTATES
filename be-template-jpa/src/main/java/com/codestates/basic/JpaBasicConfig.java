package com.codestates.basic;

import com.codestates.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration  // Spring Bean 검색 -> @Bean
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean   // Spring Bean 등록
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();  // DB의 테이블에 저장

        return args -> {
            // TODO 이 곳에 학습할 코드를 타이핑하세요!
            example01();    // 엔티티 클래스 객체를 JPA 영속성 컨텍스트에 저장
            example02();    // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에 저장
            example03();    // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에 일괄 저장
            example04();    // 엔티티 변경 감지를 통하여, JPA 영속성 컨텍스트와 DB 테이블에 엔티티 업데이트
            example05();    // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에서 삭제
        };
    }

    private void example01() {  // 엔티티 클래스 객체를 JPA 영속성 컨텍스트에 저장
        Member member = new Member("example01@mail.com");

        em.persist(member); // 영속성 컨텍스트 저장

        // find(조회 할 엔티티 클래스의 타입, 조회 할 엔티티 클래스의 식별자 값)
        Member resultMember = em.find(Member.class, 1L);    // 영속성 컨텍스트 조회
        System.out.println("ID + " + resultMember.getMemberId() + "email : " +resultMember.getEmail());
    }

    private void example02() {  // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에 저장
        tx.begin(); // Transaction 시작

        Member member = new Member("example02@mail.com");

        em.persist(member); // 영속성 컨텍스트 저장

        tx.commit();    // DB 테이블에 저장(INSERT)

        Member resultMember1 = em.find(Member.class, 1L);   // 영속성 컨텍스트 조회

        System.out.println("ID : " + resultMember1.getMemberId() + "email : " + resultMember1.getEmail());

        Member resultMember2 = em.find(Member.class, 2L);

        System.out.println(resultMember2 = null);   // 결과 값 ture
    }

    private void example03() {  // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에 일괄 저장
        tx.begin();

        Member member1 = new Member("example1@mail.com");
        Member member2 = new Member("example2@mail.com");

        em.persist(member1);    // 영속성 컨텍스트 저장
        em.persist(member2);    // 영속성 컨텍스트 저장

        tx.commit();    // DB 테이블 저장(INSERT)
    }

    private void example04() {  // 엔티티 변경 감지를 통하여, JPA 영속성 컨텍스트와 DB 테이블에 엔티티 업데이트
        tx.begin();
        em.persist(new Member("example04@mail.com"));   // 영속성 컨텍스트 저장
        tx.commit();    // DB 테이블 저장(INSERT)

        tx.commit();
        Member member1 = em.find(Member.class, 1L); // 영속성 컨텍스트 조회
        member1.setEmail("ex04@mail.com");    // 이메일 정보 변경
        tx.commit();    // DB 테이블 저장(UPDATE)
    }

    private void example05() {  // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에서 삭제
        tx.begin();
        em.persist(new Member("example05@mail.com"));   // 영속성 컨텍스트 저장
        tx.commit();    // DB 테이블 저장(INSERT)

        tx.begin(); // 저장된 객체를 영속성 컨텍스트에서 조회
        Member member = em.find(Member.class, 1L);
        em.remove(member);  // 삭제
        tx.commit();    // 삭제된 객체를 DB 테이블에서 삭제(DELETE)
    }
}
