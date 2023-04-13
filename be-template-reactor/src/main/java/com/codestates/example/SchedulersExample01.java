package com.codestates.example;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

// Scheduler를 추가하지 않는 경우
@Slf4j
public class SchedulersExample01 {
    public static void main(String[] args) {
        Flux
                .range(1, 10)   // 1부터 10개의 숫자를 Emit
                .filter(n -> n % 2 == 0)    // 짝수 필터링
                .map(n -> n * 2)    // x2
                .subscribe(data -> log.info("onNext : {}", data));  // 변경된 결과 출력
    }
}
