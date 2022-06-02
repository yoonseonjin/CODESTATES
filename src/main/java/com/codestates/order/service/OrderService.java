package com.codestates.order.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public Order createOrder(Order order) {
        // TODO should business logic

        // TODO order 객체는 나중에 DB에 저장 후, 되돌려 받는 것으로 변경 필요.
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Order findOrder(long orderId) {
        // TODO should business logic

        // TODO order 객체는 나중에 DB에서 조회 하는 것으로 변경 필요.
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    // 주문 수정 메서드는 사용하지 않습니다.

    public List<Order> findOrders() {
        // TODO should business logic

        // TODO order 객체는 나중에 DB에서 조회하는 것으로 변경 필요.
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public void cancelOrder(long orderId) {
        // TODO should business logic
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }
}
