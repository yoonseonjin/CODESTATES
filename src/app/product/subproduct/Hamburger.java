package app.product.subproduct;

import app.product.Product;

public class Hamburger extends Product {
    // 필드 : 클래스의 속성을 나태내는 변수
    private boolean isBurgerSet;    // 인스턴스 변수 : 클래스에 의해 정의된 변수
    private int burgerSetPrice;

    // 생성자 : 클래스의 객체를 생성하는 역할
    // 클래스명(파라미터) { ... }
    public Hamburger(int id, String name, int price, int kcal, boolean isBurgerSet, int burgerSetPrice) {
        super(id, name, price, kcal);   // super() : 상위클래스의 생성자 호출
        this.isBurgerSet = isBurgerSet; // this.인스턴스 변수 = 파라미터
        this.burgerSetPrice = burgerSetPrice;
    }

    public Hamburger(Hamburger hamburger) { // 생성자 오버로딩
        super(hamburger.getName(), hamburger.getPrice(), hamburger.getKcal());
        this.isBurgerSet = hamburger.isBurgerSet();
        this.burgerSetPrice = getBurgerSetPrice();
    }

    // 메서드 : 클래스의 기능을 나타내는 함수
    // 자바제어자 반환타입 메서드명(파라미터) { ... }
    public boolean isBurgerSet() {
        return isBurgerSet;
    }   // 인스턴스 메서드

    public void setIsBurgerSet(boolean isBurgerSet) {
        this.isBurgerSet = isBurgerSet;
    }

    public int getBurgerSetPrice() {
        return burgerSetPrice;
    }

    public void setBurgerSetPrice(int burgerSetPrice) {
        this.burgerSetPrice = burgerSetPrice;
    }
}
