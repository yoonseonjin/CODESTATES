package com.codestates.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {    // Error 정보
    private List<FieldError> filedErrors;   // MethodArgumentNotValidException 에러 정보 -> DTO 클래스 필드 유효성 검증 실패
    private List<ConstraintViolationError> violationErrors; // ConstraintViolationException 에러 정보 -> URI 변수 값 유효성 검증 실패

    // private 클래스는 new 로 객체 생성 불가, of() 메서드로 객체 생성 가능
    private ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
         this.filedErrors = fieldErrors;
         this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    @Getter
    public static class FieldError {    // DTO 클래스 필드 유효성 검증 실패
        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors
                    .stream()
                    .map(error ->
                            new FieldError(
                                    error.getField(),
                                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),    // 조건문 ? 참 : 거짓
                                    error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError {   // URI 변수 값 유효성 검증 실패
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations
                    .stream()
                    .map(constraintViolation ->
                            new ConstraintViolationError(
                                    constraintViolation.getPropertyPath().toString(),
                                    constraintViolation.getInvalidValue().toString(),
                                    constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}
