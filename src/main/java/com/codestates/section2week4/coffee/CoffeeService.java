package com.codestates.section2week4.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeService {
    // DI
    private static CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public void createCoffee(Coffee coffee) {
        coffeeRepository.postCoffee(coffee);
    }

    public Coffee editCoffee(Long coffeeId, String korName, int price) {
        return coffeeRepository.patchCoffee(coffeeId, korName, price);
    }

    public Coffee getCoffee(Long coffeeId) {
        return coffeeRepository.getCoffee(coffeeId);
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeRepository.deleteCoffee(coffeeId);
    }
}
