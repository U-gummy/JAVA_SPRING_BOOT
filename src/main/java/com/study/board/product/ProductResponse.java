package com.study.board.coupon4;

public record ProductResponse(Long id, String name, String code, Integer discountRate) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getCode(), product.getDiscountRate());
    }
}
