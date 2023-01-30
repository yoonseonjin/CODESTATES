package com.codestates.example.operators.transformation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

// zip() 예제
@Slf4j
public class ZipExample {
    public static void main(String[] args) throws InterruptedException {
        //  interval() : 파라미터로 전달한 시간을 주기로 0부터 1씩 증가한 숫자를 Emit
        Flux<Long> source1 = Flux.interval(Duration.ofMillis(200L)).take(4);    // 0.2초에 한 번씩

        Flux<Long> source2 = Flux.interval(Duration.ofMillis(400L)).take(6);    // 0.4초에 한 번씩

        Flux
                .zip(source1, source2, (data1, data2) -> data1 + data2) // Emit 시점과 개수가 다르지만, 대기 후 함께 전달 받는다
                .subscribe(data -> log.info("onNext : {}", data));

        Thread.sleep(3000L);
    }
}
