package com.codestates.example;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

// subscribeOn() Operator를 사용해서, Scheduler를 추가하는 경우
@Slf4j
public class SchedulersExample02 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(1, 10)   // 1부터 10개의 숫자를 Emit
                .doOnSubscribe(subscription -> log.info("doOnSubscribe()")) // doOnSubscribe() : 구독 후의 트리거 Operator
                .subscribeOn(Schedulers.boundedElastic())   // subscribeOn() : 구독 후 실행되는 Operator의 thread를 Scheduler에서 지정한 thread로 변경
                .filter(n -> n % 2 == 0)    // 짝수 필터링
                .map(n -> n * 2)    // x2
                .subscribe(data -> log.info("onNext : {}", data));  // 변경된 결과 출력

        Thread.sleep(100L); // main thread 와 demon thread 함께 종료
    }
}
