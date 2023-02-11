package com.codestates.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table("ORDER_COFFEE")  // 테이블 명 지정
public class CoffeeRef {
    @Id // Spring Data JDBC 엔티티 객체의 식별자로 사용할 필드에 적용 -> CoffeeRef 클래스와 ORDER_COFFEE 테이블을 매핑
    private long orderCoffeeId;
    private long coffeeId;
    private int quantity;
}
