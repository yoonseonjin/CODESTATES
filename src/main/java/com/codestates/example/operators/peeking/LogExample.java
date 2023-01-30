package com.codestates.example.operators.peeking;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

// log() 예제
public class LogExample {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600))
                .log()  // log() : Publisher에서 발생하는 Signal 이벤트를 log로 출력하여 확인 가능
                .reduce((a, b) -> a + b)
                .log()
                .subscribe(System.out::println);
    }
}
