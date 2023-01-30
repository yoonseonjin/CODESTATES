package com.codestates.example;

import reactor.core.publisher.Flux;

// map() 예제
public class MarbleDiagramExample {
    public static void main(String[] args) {
        Flux
                .just("Green-Circle", "Orange-Circle", "Blue-Circle")   // 세 개의 문자열을 Emit
                .map(figure -> figure.replace("Circle", "Rectangle"))   // Circle -> Rectangle 변경, 대소문자 구분
                .subscribe(System.out::println);    // 변경된 문자열 출력
    }
}
