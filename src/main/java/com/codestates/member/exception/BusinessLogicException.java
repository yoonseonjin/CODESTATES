package com.codestates.member.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {  // 서비스 계층에서 사용할 Custom Exception 정의
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());  // super() : 상위 클래스 생성자 호출
        this.exceptionCode = exceptionCode;
    }
}
