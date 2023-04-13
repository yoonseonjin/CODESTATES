package com.codestates.example.operators.errors;

import com.codestates.example.operators.sample_data.Coffee;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

// error() 예제
@Slf4j
public class ErrorExample {
    public static void main(String[] args) {
        Mono
                .justOrEmpty(findVerifiedCoffee())  // justOrEmpty() : 파라미터로 전달되는 데이터소스가 null 값이더라도, 에러 미발생 <-> just() : null 값 에러 발생
                .switchIfEmpty(Mono.error(new RuntimeException("NOT Found Coffee")))    // switchIfEmpty() : Upstream에서 전달되는 데이터가 null 값이라면, 대체 동작 설정 가능
                .subscribe(
                        data -> log.info("{} : {}", data.getKorName(), data.getPrice()),
                        error -> log.error("onError : {}", error.getMessage()));    // 에러 메세지 출력
    }

    private static Coffee findVerifiedCoffee() {

        return null;
    }
}
