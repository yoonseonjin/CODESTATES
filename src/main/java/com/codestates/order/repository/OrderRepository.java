package com.codestates.order.repository;

import com.codestates.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

// <Order 엔티티 클래스, Order 엔티티 클래스의 @Id가 붙은 변수 타입>
public interface OrderRepository extends CrudRepository<Order, Long> {
}
