package app;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Cart {
    private Scanner scanner = new Scanner(System.in);
    private Product[] items = new Product[0];
    private ProductRepository productRepository;
    private Menu menu;

    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }

    public void printCart() {   // 장바구니 출력
        System.out.println("장바구니");
        System.out.println("-".repeat(60));

        printCartItemDetails(); // 장바구니에 담은 상품들의 옵션 정보

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateTotalPrice()); // 장바구니에 담은 상품들의 금액 합산

        System.out.println("이번으로 돌아가려면 엔터를 누르세요");
        scanner.nextLine();
    }

    protected void printCartItemDetails() {    // 장바구니에 담은 상품들의 옵션 정보
        for (Product product : items) {
            // if (prdocut가 BurgerSet의 인스턴스라면) 세트 정보 출력
            if (product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf("%s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                        product.getName(), product.getPrice(),
                        burgerSet.getSide().getName(), burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "있음" : "없음");
            }
            // else if (product가 Hamburger의 인스턴스라면) (단품) 출력
            else if (product instanceof Hamburger) {
                System.out.printf("%-8s %6d원 (단품)\n", product.getName(), product.getPrice());
            }
            // else if (product가 Side의 인스턴스라면) 케첩 개수 출력
            else if (product instanceof Side) {
                System.out.printf("%-8s %6d원 (케첩 %d개)\n", product.getName(), product.getPrice(), ((Side) product).getKetchup());    // 다운 캐스팅
            }
            // else if (product가 Drinks의 인스턴스라면) 빨대 유무 출력
            else if (product instanceof Drink) {
                System.out.printf("%-8s %6d원 (빨대 %s)\n", product.getName(), product.getPrice(), ((Drink) product).hasStraw() ? "있음" : "없음");    // 다운 캐스팅
            }
        }
    }

    protected int calculateTotalPrice() {   // 장바구니에 담은 상품들의 금액 합산
        int totalPrice = 0;

        for (Product product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    private void chooseOption(Product product) {    // 상품 옵션 설정
        String input;

        // 인스턴스 instanceof 클래스 :
        // 좌항의 인스턴스(product)를 생성한 클래스가(Product)가 우항 클래스(Side)와 같거나, 하위 클래스라면 true 반환

        // if (product가 Hamburger의 인스턴스인 경우)
        if (product instanceof Hamburger) {
            System.out.printf("단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n", product.getPrice(), ((Hamburger) product).getBurgerSetPrice());
            input = scanner.nextLine(); // input = 사용자 입력
            // if (input이 "2"라면) product의 is BurgerSet을 ture로 변경
            if (input.equals("2")) ((Hamburger) product).setIsBurgerSet(true);  // 다운 캐스팅
        }
        // else if (product가 Side의 인스턴스인 경우)
        else if (product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = scanner.nextLine(); // input = 사용자 입력
            // product의 ketchup에 input 할당
            ((Side) product).setKetchup(Integer.parseInt(input));   // 다운 캐스팅
        }
        // else if (product가 Drink의 인스턴스인 경우)
        else if (product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = scanner.nextLine(); // input = 사용자 입력
            // if (input이 "2"라면) product의 hasStraw를 false로 변경
            if (input.equals("2")) ((Drink) product).setHasStraw(false);    // 다운 캐스팅
        }
    }

    public void addToCart(int productId) {  // 상품 옵션 설정 호출
        // 상품의 id값을 인자로 받아와, 인자를 통해 상품을 productRepository 에서 검색한 다음, 해당되는 상품을 product에 할당
        Product product = productRepository.findById(productId);

        chooseOption(product);

        if (product instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) product;
            if (hamburger.isBurgerSet()) product = composeSet(hamburger);   // composeSet()의 반환값을 product에 할당
        }

        Product newProduct;
        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else if (product instanceof Drink) newProduct = new Drink((Drink) product);
        else newProduct = new BurgerSet((BurgerSet) product);

        Product[] newItems = new Product[items.length +1];  // 배열 확장
        System.arraycopy(items, 0, newItems, 0, items.length);
        // 옵션을 선택하거나 세트를 구성하는 등의 동작 끝
        // items 배열의 길이를 확장하고, product를 items에 추가
        newItems[newItems.length -1] = newProduct;
        items = newItems;

        System.out.printf("%s를(을) 장바구니에 담았습니다.\n", product.getName());
    }

    private BurgerSet composeSet(Hamburger hamburger) { // 세트 구성
        System.out.println("사이드를 골라주세요");
        menu.printSides(false);  // 사이드 메뉴 출력

        String sideId = scanner.nextLine(); // String sideId = 사용자 입력 받기
        Side side = (Side) productRepository.findById(Integer.parseInt(sideId));    // Side side = sideId를 id로 가지는 상품 검색
        chooseOption(side);    // 사이드 옵션 선택

        System.out.println("음료를 골라주세요");
        menu.printDrinks(false); // 음료 메뉴 출력

        String drinkId = scanner.nextLine();    // String drinkId = 사용자 입력 받기
        Drink drink = (Drink) productRepository.findById(Integer.parseInt(drinkId));    // Drink drink = drinkId를 id로 가지는 상품 검색
        chooseOption(drink);    // 음료 옵션 선택

        String name = hamburger.getName() + "세트";   // String name = hamburger의 이름 + "세트"
        int price = hamburger.getBurgerSetPrice();  // int price = hamburger의 BurgerSetPrice 필드의 값
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();  // int kcal = 햄버거, 사이드, 드링크의 칼로리 총합

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }
}
