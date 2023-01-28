package com.codestates.example;

import reactor.core.publisher.Mono;

// 리액티브 프로그래밍 기본 구조 2
public class HelloReactiveExample02 {
    public static void main(String[] args) {
        // 리액티브 프로그래밍은 선언형 프로그래밍 방식(Stream AP의 메서드 체인)으로 구성된다
        Mono.just("Hello Reactive").subscribe(message -> System.out.println(message));
    }
}
