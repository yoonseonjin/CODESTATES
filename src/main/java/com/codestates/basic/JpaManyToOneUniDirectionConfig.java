package com.codestates.basic;

import com.codestates.entity.Member;
import com.codestates.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneUniDirectionConfig {   // N:1 매핑 -> 주문 : 회원
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {mappingManyToOneUniDirection();};
    }

    private void mappingManyToOneUniDirection() {
        tx.begin();
        Member member = new Member("N1@mail.com", "다대일", "010-0000-0000");
        em.persist(member); // 영속성 컨텍스트에 회원 정보 저장

        Order order = new Order();
        order.addMember(member);    // 저장한 회원 정보의 주문 정보를 저장하기 위해 order 객체에 member 객체 추가
        em.persist(order);  // 영속성 컨텍스트에 주문 정보 저장

        tx.commit();    // DB 테이블에 저장(INSERT)

        // find(조회 할 엔티티 클래스의 타입, 조회 할 엔티티 클래스의 식별자 값)
        Order findOrder = em.find(Order.class, 1L); // 영속성 컨텍스트 조회

        // 주문에 해당하는 회원 정보 출력 -> 다른 객체를 통해 다른 객체의 정보를 얻을 수 있다 -> 객체 그래프 탐색
        System.out.println("findOrder : " + findOrder.getMember().getMemberId() + ", " +findOrder.getMember().getEmail());
    }
}
