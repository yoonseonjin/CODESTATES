package com.codestates.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    // @Enumerated : enum 타입 매핑, EnumType.STRING : enum 타입의 이름을 테이블에 저장
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 생성 시간

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now(); // 수정 시간

    @ManyToOne  // N:1 -> N에게 설정
    @JoinColumn(name = "MEMBER_ID") // 외래키에 해당하는 컬렴명 -> N에게 설정
    private Member member;  // 외래키

    public void addMember(Member member) {
        this.member = member;
    }

    public enum OrderStatus {   // enum : 열거형 상수(고정값)
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
