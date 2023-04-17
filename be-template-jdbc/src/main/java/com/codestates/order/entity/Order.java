package com.codestates.order.entity;

import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

// TODO V10
@Getter
@Setter
@Table("ORDERS")    // Order은 SQL 쿼리문에서 사용하는 예약어이기 때문에, ORDERS로 테이블 명 지정
public class Order {
    @Id // Spring Data JDBC 엔티티 객체의 식별자로 사용할 필드에 적용 -> Order 클래스와 ORDERS 테이블을 매핑
    private long orderId;

    // 테이블 외래키처럼 memberId를 추가해서 참조하도록 한다.
    // Member - Order -> 1 : N
    private AggregateReference<Member, Long> memberId;

    // Order - Coffee -> N : N
    // idColumn : 자식 테이블에 추가되는 외래키에 해당되는 컬렴명 지정
    // keyColumn : 외래키를 포함하고 있는 테이블의 기본키 컬렴명 지정
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ORDER_COFFEE_ID")
    private Set<CoffeeRef> orderCoffees = new LinkedHashSet<>();

    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;    // 주문 상태 정보, 기본 값 : ORDER_REQUEST(주문 요청)
    private LocalDateTime createdAt = LocalDateTime.now();    // 주문 등록 시간

    public enum OrderStatus {   // 주문의 상태
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}

