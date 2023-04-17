package com.codestates.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Message {  // 데이터베이스의 테이블에 해당하는 Message Entity 클래스
    // @ID : 애너테이션 추가 시, 해당 Entity의 고유 식별자 역할을 하고 식별자는 데이터베이스의 Primary key로 지정한 컬럼에 해당된다
    @Id
    private long messageId;
    private String message;
}
