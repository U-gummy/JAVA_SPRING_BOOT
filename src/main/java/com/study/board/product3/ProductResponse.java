package com.study.board.product3;

public record ProductResponse(Long id, String name, String productCode) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getProductCode());
    }
}
