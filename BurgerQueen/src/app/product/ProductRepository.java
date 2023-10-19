package app.product;

import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductRepository {    // 상품 정보 저장
    private Product[] products = {
            new Hamburger(1, "새우버거", 3500, 500, false, 4500),
            new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
            new Side(3, "감자튀김", 1000, 300, 1),
            new Side(4, "어니언링", 1000, 300, 1),
            new Drink(5, "코카콜라", 1000, 200, true),
            new Drink(6, "제로콜라", 1000, 0, true)
    };

    public Product[] getAllProducts() { // 상품 정보 접근 가능
        return products;
    }

    public Product findById(int productId) {    // 상품 검색 코드, 상품 정보 접근 가능
        for (Product product : products) {
            if (product.getId() == productId) return product;
        }
        return null;
    }
}
