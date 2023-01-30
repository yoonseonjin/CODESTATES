package com.codestates.example.operators.errors;

import com.codestates.example.operators.sample_data.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

// timeout(), retry() 예제
@Slf4j
public class TimeoutRetryExample {
    public static void main(String[] args) throws InterruptedException {
        getCoffees()
                .collect(Collectors.toSet())    // 재시작 시, 중복 제거
                .subscribe(bookSet -> bookSet
                        .stream()
                        .forEach(data -> log.info("{} :{}", data.getKorName(), data.getPrice())));

        Thread.sleep(12000);
    }

    private static Flux<Coffee> getCoffees() {
        final int[] count = {0};
        return Flux
                .fromIterable(SampleData.coffeeList)
                .delayElements(Duration.ofMillis(500))  // delayElements() : 입력으로 주어진 시간만큼 각각의 데이터 Emit을 지연 -> 0.5초 지연
                .map(coffee -> {
                    try {
                        count[0]++;
                        if (count[0] == 3) Thread.sleep(2000);  // 3번째 데이터 2초 추가 지연 설정 -> 2.5초 지연
                    } catch (InterruptedException e) {
                    }

                    return coffee;
                })
                .timeout(Duration.ofSeconds(2)) // 2초 안에 데이터 Emit이 실행되지 않는다면, onError Signal 이벤트 발생 설정
                .retry(1)   // 1회 재구독 -> Sequence 재시작
                .doOnNext(coffee -> log.info("getCoffees > doOnNext : {}, {}", coffee.getKorName(), coffee.getPrice()));
    }
}
