package com.codestates.coffee;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    @NotBlank(message = "공백만으로 구성되지 않아야 합니다.")
    private String korName;

    @NotBlank
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "영문만 허용하며, 공백만으로 구성되지 않아야 합니다.")
    private String engName;

    @Range(min = 100, max = 50000)  // 100 이상 50,000 이하의 숫자만 허용
    private int price;

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public int getPrice() {
        return price;
    }
}
