package com.codestates.example.operators.create;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

// fromStream() 예제
public class FromStreamExample {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600)) // stream이 포함하는 데이터를 순차적으로 Emit
                .reduce((a, b) -> a + b)    // reduce() : Upstream이 Emit한 데이터를 순차적으로 누적처리하는 Operator -> 전달 받아 합계 구하기
                .subscribe(System.out::println);
    }
}
