package com.codestates.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(nullable = false, length = 50)
    private String korName;

    @Column(nullable = false, length = 50)
    private String engName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 3)
    private String coffeeCode;  // 커피 고유 식별 코드

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 생성 시간

    @Column(nullable = false, name = "LAST_MODIFOED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now(); // 수정 시간
}
