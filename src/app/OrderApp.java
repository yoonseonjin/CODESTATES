package app;

import app.product.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();  // 클래스명 참조_변수명 = new 생성자();
        Product[] products = productRepository.getAllProducts();
        Menu menu = new Menu(products);
        Cart cart = new Cart(productRepository, menu);
        Order order = new Order(cart);

        System.out.println("BurgerQueen Order Service 🍔");  // 오더 서비스 시작

        while (true) {
            menu.printMenu();   // 메뉴 출력
            String input = scanner.nextLine();

            if (input.equals("+")) {    // 사용자 입력이 "+"인 경우
                order.makeOrder();  // 주문 내역 출력
                break;
            }
            else {
                int menuNumber = Integer.parseInt(input);   // Integer.parseInt() : string -> int

                if (menuNumber == 0) cart.printCart();   // 장바구니 출력
                else if (1 <= menuNumber && menuNumber <= products.length) {    // 사용자 입력이 1부터 메뉴 마지막 번호에 해당하는 경우
                    cart.addToCart(menuNumber);
                }
            }
        }
    }
}
