package com.codestates.example.operators.peeking;

import com.codestates.example.operators.sample_data.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

// doOnNext() 예제
@Slf4j
public class DoOnNextExample {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coffeeList)
                .doOnNext(coffee -> validateCoffee(coffee)) // doOnNext() : Emit되는 데이터의 유효성 검증 진행
                .subscribe(data -> log.info("{} : {}", data.getKorName(), data.getPrice()));
    }

    private static void validateCoffee(Coffee coffee) { // 유효성 검증
        if (coffee == null) throw new RuntimeException("NOT Found Coffee");
    }
}
