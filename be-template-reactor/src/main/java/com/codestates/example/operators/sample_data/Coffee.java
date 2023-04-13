package com.codestates.example.operators.sample_data;

import lombok.Getter;

@Getter
public class Coffee {
    String korName;
    String EngName;
    int Price;
    String CoffeeId;

    Coffee(String korName, String EngName, int Price, String CoffeeId) {
        this.korName = korName;
        this.EngName = EngName;
        this.Price = Price;
        this.CoffeeId = CoffeeId;
    }
}
