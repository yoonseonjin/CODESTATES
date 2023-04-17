package com.codestates.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class Coffee {
    @Id // Spring Data JDBC 엔티티 객체의 식별자로 사용할 필드에 적용 -> Coffee 클래스와 COFFEE 테이블을 매핑
    private long coffeeId;
    private String korName;
    private String engName;
    private Integer price;
    private String coffeeCode;  // 중복 등록 체크
}
