package com.codestates.coffee.service;

import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.repository.CoffeeRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private CoffeeRepository coffeeRepository;

    // CoffeeRepository DI
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public Coffee createCoffee(Coffee coffee) {
        // 커피 코드를 대문자로 변경, 사용자 편의성을 위한 기능 구현
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

        // 이미 등록된 커피 코드인지 검증
        verifyExistCoffee(coffeeCode);
        coffee.setCoffeeCode(coffeeCode);

        // 커피 정보 저장
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee) {
        // 이미 존재하는 커피인지 검증
        Coffee findCoffee = findVerifiedCoffee(coffee.getCoffeeId());

        // 이름 정보와 가격 정보 업데이트
        Optional.ofNullable(coffee.getKorName()).ifPresent(korName -> findCoffee.setKorName(korName));
        Optional.ofNullable(coffee.getEngName()).ifPresent(engName -> findCoffee.setEngName(engName));
        Optional.ofNullable(coffee.getPrice()).ifPresent(price -> findCoffee.setPrice(price));

        // 커피 정보 업데이트
        return coffeeRepository.save(findCoffee);
    }

    public Coffee findCoffee(long coffeeId) {
        // 특정 커피 정보 조회
        return findVerifiedCoffeeByQuery(coffeeId);
    }

    public List<Coffee> findCoffees() {
        // 모든 커피 정보 조회
        return (List<Coffee>) coffeeRepository.findAll();   // findAll() 메서드의 리턴값이 Iterable<T>이기 때문에 List<Coffee>로 캐스팅
    }

    public void deleteCoffee(long coffeeId) {
        Coffee coffee = findVerifiedCoffee(coffeeId);

        // 특정 커피 정보 삭제
        coffeeRepository.delete(coffee);
    }

    // 주문에 해당하는 커피 정보 조회
    public List<Coffee> findOrderedCoffees(Order order) {
        return order.getOrderCoffees().stream().map(coffeeRef -> findCoffee(coffeeRef.getCoffeeId())).collect(Collectors.toList());
    }

    // 이미 등록된 커피 코드인지 검증
    private void verifyExistCoffee(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if (coffee.isPresent())
            throw new BusinessLogicException(ExceptionCode.COFFEE_CODE_EXISTS);
    }

    // 이미 존재하는 커피인지 검증
    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        Coffee findCoffee = optionalCoffee.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));

        return findCoffee;
    }

    //
    private Coffee findVerifiedCoffeeByQuery(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findByCoffee(coffeeId);
        Coffee findCoffee = optionalCoffee.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findCoffee;
    }
}
