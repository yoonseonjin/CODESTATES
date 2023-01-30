package com.codestates.example.operators.transformation;

import reactor.core.publisher.Flux;

// concat() 예제 1
public class ConcatExample01 {
    public static void main(String[] args) {
        Flux
                .concat(Flux.just("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                        Flux.just("Saturday", "Sunday"))    // concat() : 이어 붙인다
                .subscribe(System.out::println);
    }
}
