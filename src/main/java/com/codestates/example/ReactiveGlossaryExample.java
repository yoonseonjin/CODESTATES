package com.codestates.example;

import reactor.core.publisher.Flux;

import java.util.List;

// 리액티브 프로그래맹 기본 구조 3
public class ReactiveGlossaryExample {
    public static void main(String[] args) {
        Flux    // Publisher
                .fromIterable(List.of(1, 3,6, 7, 8, 11))    // Operator
                .filter(number -> number > 4 && (number % 2 == 0))  // Operator
                .reduce((n1, n2) -> n1 + n2)    // Operator
                .subscribe(System.out::println);    // Subscriber
    }
}
