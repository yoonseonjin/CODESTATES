package com.codestates.example.operators.create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

// create() : FluxSink라는 람다 파라미터를 가지는 람다 표현식
@Slf4j
public class CreateExample {
    private static List<Integer> source = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19); // 20 이하 홀수
    public static void main(String[] args) {
        Flux.create((FluxSink<Integer> sink) -> {   // FluxSink : 프로그래밍 방식의 Signal 이벤트를 발생으로 Sequence 진행 기능
            // onRequest() : Subscriber에서 데이터를 요청하면, 람다 표현식 실행
            sink.onRequest(n -> {
                for (int i = 0; i < source.size(); i++) {
                    sink.next(source.get(i));   // next() 메서드로 List source의 원소 Emit
                }
                sink.complete();    // List source의 모든 원소를 Emit했으므로, Sequence 종료를 위해 compleate() 호출
            });

            // Sequence 종료 직전 후처리 작업을 위해 onDispose() 호출
            sink.onDispose(() -> log.info("clean up"));
        }).subscribe(data -> log.info("onNext : {}", data));
    }
}
