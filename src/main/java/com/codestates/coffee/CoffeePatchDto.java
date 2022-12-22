package com.codestates.coffee;

import com.codestates.member.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.util.Optional;

public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "커피명(한글)은 공백만으로 구성되지 않아야 합니다.")
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",  message = "영문만 허용하며, 공백만으로 구성되지 않아야 합니다.")
    private String engName;
    
    private Optional<@Range(min = 100, max = 50000) Integer> price = Optional.empty();
    // Optional 생성
    // Optional.empty() : Optional 타입의 참조변수를 기본값을 초기화

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public Optional<Integer> getPrice() {
        return price;
    }
}
