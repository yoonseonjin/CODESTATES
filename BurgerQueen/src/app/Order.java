package app;

public class Order {
    private Cart cart;

    public Order(Cart cart) {
        this.cart = cart;
    }

    public void makeOrder() {   // 주문 내역 출력
        System.out.println("주문이 완료되었습니다");
        System.out.println("주문 내역은 다음과 같습니다");
        System.out.println("-".repeat(60));

        cart.printCartItemDetails(); // 장바구니에 담은 상품들의 옵션 정보

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계 : %d원\n", cart.calculateTotalPrice()); // 장바구니에 담은 상품들의 금액 합산
    }
}
