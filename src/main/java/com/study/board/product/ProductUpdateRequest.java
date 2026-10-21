package com.study.board.product;

import jakarta.validation.constraints.*;

public record ProductUpdateRequest(
        @NotBlank(message = "상품 이름은 필수입니다.") String name,

        @NotBlank(message = "상품 코드는 필수입니다.")
        @Size(min = 4, max = 20, message = "상품 코드는 4~20자여야 합니다.") String productCode
) {
}
