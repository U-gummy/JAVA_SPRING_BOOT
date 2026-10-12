package com.study.board.common;

import com.study.board.coupon4.CouponNotFoundException;
import com.study.board.customer4.CustomerNotFoundException;
import com.study.board.product3.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCouponNotFound(CouponNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        List<FieldErrorResponse> errors = e.getBindingResult() // 검증 결과 전체가 담긴 객체
                .getFieldErrors() // 그중 필드별 에러 목록 → List<FieldError>
                .stream() // 에러를 하나씩 꺼내서
                .map(error -> new FieldErrorResponse(error.getField(), error.getDefaultMessage())) // 응답 record로 바꾸고
                .toList(); // List로 담기

        ValidationErrorResponse response = new ValidationErrorResponse("입력값이 올바르지 않습니다.", errors);
        return ResponseEntity.badRequest().body(response);
    }
}

