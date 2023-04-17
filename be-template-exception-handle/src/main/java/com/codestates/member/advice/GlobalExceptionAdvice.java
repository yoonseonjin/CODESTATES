package com.codestates.member.advice;

import com.codestates.member.exception.BusinessLogicException;
import com.codestates.member.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

// @RestControllerAdvice의 예외 처리
@RestControllerAdvice
public class GlobalExceptionAdvice {    // Controller 클래스의 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) { // 메서드 인수가 유효하지 않은 예외 처리
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {   // 제약 조건 위반 예외 처리
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }

    /*
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(RuntimeException e) {  // 리소스를 찾을 수 없음 예외 처리
        System.out.println(e.getMessage());

        // ErrorResponse 수정 생략, 추상적인 조건의 예외이기 때문에 메서드 변경명 변경 필요

        return null;
    }
    */

    // handleResourceNotFoundException 개선 코드
    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {  // 비즈니스 로직 예외 처리
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());

        // ErrorResponse 수정 생략

        return new ResponseEntity<>(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}
