package com.codestates.section2week4.coffee;

public class CoffeeTest {   // 커피 로직 테스트 구현
    public static void main(String[] args) {
        CoffeeService coffeeService = new CoffeeService();

        Coffee coffee = new Coffee(0L, "바닐라 라떼", "Vanilla Latte", 5500);
        coffeeService.createCoffee(coffee);

        if (coffeeService.getCoffee(0L).getKorName().equals(coffee.getKorName())) {
            System.out.println("바닐라 라떼가 등록되었습니다.");
        }

        coffeeService.editCoffee(0L, "바닐라 라떼", 3000);   // 수정

        if (coffeeService.getCoffee(0L).getPrice() == 3000) {
            System.out.println("바닐라 라떼의 금액이 정상적으로 변경되었습니다.");
        }

        coffeeService.deleteCoffee(0L); // 삭제

        if (coffeeService.getCoffee(0L) == null) {
            System.out.println("바닐라 라떼가 정상적으로 삭제되었습니다.");
        }
    }
}
