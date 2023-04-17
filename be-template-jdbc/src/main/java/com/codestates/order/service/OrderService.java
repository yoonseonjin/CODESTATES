package com.codestates.order.service;

import com.codestates.coffee.service.CoffeeService;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.service.MemberService;
import com.codestates.order.entity.Order;
import com.codestates.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    // DI
    public OrderService(OrderRepository orderRepository, MemberService memberService, CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order) {
        // 회원이 존재하는지 검증
        memberService.findVerifiedMember(order.getMemberId().getId());

        // 커피가 존재하는지 검증
        order.getOrderCoffees().stream().forEach(coffeeRef -> {coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId());});

        // 주문 정보 저장
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        // 이미 존재하는 주문인지 검증
        return findVerifiedOrder(orderId);
    }

    // TODO 주문 상태 수정 메서드는 JPA 학습에서 추가됩니다.

    public List<Order> findOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public void cancelOrder(long orderId) {
        // 이미 존재하는 주문인지 검증
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        // OrderStatus의 STEP이 2 미만일 경우 ORDER_CONFIRM에만 주문 취소
        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }

        // 주문 정보 취소
        findOrder.setOrderStatus((Order.OrderStatus.ORDER_CANCEL));
        // 주문 정보 저장
        orderRepository.save(findOrder);
    }

    //
    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder = optionalOrder.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
