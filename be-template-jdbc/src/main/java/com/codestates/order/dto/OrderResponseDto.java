package com.codestates.order.dto;

import com.codestates.coffee.dto.CoffeeResponseDto;
import com.codestates.order.entity.Order;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto { // 응답 데이터
    private long orderId;
    private long memberId;
    private Order.OrderStatus orderStatus;  // 주문 상태
    private List<OrderCoffeeResponseDto> orderCoffees;   // 커피 주문 수량
    private LocalDateTime createdAt;    // 주문 시간
}
