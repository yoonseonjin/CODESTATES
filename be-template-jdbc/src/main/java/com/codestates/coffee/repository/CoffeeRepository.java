package com.codestates.coffee.repository;

import com.codestates.coffee.entity.Coffee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// <Member 엔티티 클래스, Member 엔티티 클래스의 @Id가 붙은 변수 타입>
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    // Spring Data JDBC에서 지원하는 쿼리 메서드 정의를 이용한 데이터 조회 메서드 정의
    // 쿼리 메서드 정의 -> find + By + SQL 쿼리문에서 WHERE절의 컬렴명 + (WHERE절 컬럼의 조건이 되는 데이터)
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    // :coffeeId : findByCoffeeId(Long coffeeId)의 coffeeId 변수 값이 채워지는 동적 쿼리 파라미터
    @Query("SELECT * FROM COFFEE WHERE COFFEE_ID = :coffeeID")  // SQL 쿼리문 직접 작성
    Optional<Coffee> findByCoffee(Long coffeeId);

}
