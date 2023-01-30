package com.codestates.example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

// 리액터 기본 구조
public class HelloReactorExample {
    public static void main(String[] args) throws InterruptedException {
        Flux    // Reactor Sequence의 시작점, Flux -> Reactor Sequence가 여러 건의 데이터 처리를 의미
                .just("Hello", "Reactor")   // just() : Original Data Source 데이터를 Emit하는 Publisher 역할을 하는 Operator
                .map(message -> message.toUpperCase())  // map() : Publisher로부터 전달 받은 데이터를 가공하는 Operator
                .publishOn(Schedulers.parallel())   // PublishOn() : Reactor Sequence에서 Thread 유형을 변경하는 Scheduler를 지정하는 Operator
                .subscribe(System.out::println, // Publisher가 Emit한 데이터를 전달 받아서 처리
                        error -> System.out.println(error.getMessage()),    // Reactor Sequence에서 발생한 에러를 전달 받아서 처리
                        () -> System.out.println("Reactor Sequence Complete"));  // Reactor Sequence 종료

        Thread.sleep(100L); // Reactor Sequence에 Scheduler를 지정한 경우 발생하는 demon thread를 main thread 종료 시, 동시 종료 처리
    }
}
