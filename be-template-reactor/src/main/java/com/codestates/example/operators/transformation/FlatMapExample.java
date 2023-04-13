package com.codestates.example.operators.transformation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

// flatMap() 예제
@Slf4j
public class FlatMapExample {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(2, 6)    // 2~7단 범위 지정
                .flatMap(dan -> Flux
                        .range(1, 9)    // 하나의 단(1~9) 범위 지정
                        .publishOn(Schedulers.parallel())   // publishOn() : Scheduler에서 지정한 thread로 변경, 여러 번 추가 시 별도 thread로 생성
                        .map(num -> dan + "x" + num + "=" + dan * num)) // 구구단 형식의 문자열 생성
                .subscribe(log::info);

        Thread.sleep(100L);
    }
}
