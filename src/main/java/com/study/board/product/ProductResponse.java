package com.study.board.product;

public record ProductResponse(Long id, String name, String productCode, Long categoryId, String categoryName) {

    public static ProductResponse from(Product product) {
        String categoryName = product.getCategory().getName();
        Long categoryId = product.getCategory().getId();
        return new ProductResponse(product.getId(), product.getName(), product.getProductCode(), categoryId, categoryName);
    }
}
