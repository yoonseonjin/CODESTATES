package com.codestates.coffee.service;

import com.codestates.coffee.entity.Coffee;
import com.codestates.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    public Coffee createCoffee(Coffee coffee) {
        return coffee;
    }

    public Coffee updateCoffee(Coffee coffee) {
        return coffee;
    }

    public Coffee findCoffee(long coffeeId) {
        return new Coffee(coffeeId, "아메리카노", "Americano", 2500);
    }

    public List<Coffee> findCoffees() {
        return List.of(
                new Coffee(1L, "아메리카노", "Americano", 2500),
                new Coffee(2L, "카라멜 라떼", "Caramel Latte", 5000)
        );
    }

    public void deleteCoffee(long coffeeId) {
    }

    // 주문에 해당하는 커피 정보 조회
    public List<Coffee> findOrderedCoffees(Order order) {
        return null;
    }
}
