package com.codestates.example.operators.create;

import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

// fromIterable() 예제
@Slf4j
public class FromIterableExample {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coffeeList)
                .subscribe(coffee -> log.info("{} : {}", coffee.getKorName(), coffee.getPrice()));
    }
}
