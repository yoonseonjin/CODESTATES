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
public class JpaManyToOneBiDirectionConfig {    // 1:N 매핑 -> 회원 : 주문
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {mappingManyToOneBiDirection();};
    }

    private void mappingManyToOneBiDirection() {    // 엔티티 클래스 객체를 JPA 영속성 컨텍스트와 DB 테이블에 일괄 저장
        tx.begin();
        Member member = new Member("1N@mail.com", "일대다", "010-0000-0001");
        Order order = new Order();

        member.addOrder(order); // member 객체에 order 객체 추가
        order.addMember(member);

        em.persist(member); // 영속성 컨텍스트 회원 정보 저장
        em.persist(order);  // 영속성 컨텍스트 주문 정보 저장

        tx.commit();    // DB 테이블 저장(INSERT)

        Member findMember = em.find(Member.class, 1L);  // 영속성 컨텍스트 조회

        // 주문한 회원의 회원 정보를 통해 주문 정보를 가져올 수 있다
        findMember.getOrders().stream().forEach(findOrder -> {
            System.out.println("findOrder : " + findOrder.getOrderId() + findOrder.getOrderStatus());
        });
    }
}
