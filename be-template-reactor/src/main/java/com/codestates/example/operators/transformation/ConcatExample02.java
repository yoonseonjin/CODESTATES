package com.codestates.example.operators.transformation;

import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

// concat() 예제 2
@Slf4j
public class ConcatExample02 {
    public static void main(String[] args) {
        Flux
                .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                        Flux.fromIterable(SampleData.salesOfCafeB),
                        Flux.fromIterable(SampleData.salesOfCafeC))
                .reduce((a, b) -> a + b).subscribe(data -> log.info("tatal sales : {}", data)); // 전체 매출액
    }
}
