package com.study.board.coupon4;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("쿠폰을 찾을 수 없습니다. id=" + id);
    }

}



