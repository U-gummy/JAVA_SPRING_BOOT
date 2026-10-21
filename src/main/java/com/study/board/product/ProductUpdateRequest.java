package com.study.board.coupon4;

import jakarta.validation.constraints.*;

public record ProductUpdateRequest(
        @NotBlank(message = "쿠폰 이름은 필수입니다.") String name,

        @NotBlank(message = "쿠폰 코드는 필수입니다.")
        @Size(min = 4, max = 20, message = "쿠폰 코드는 4~20자여야 합니다.") String code,

        @NotNull(message = "할인율은 필수입니다.")
        @Min(value = 1, message = "할인율은 1 이상이어야 합니다.")
        @Max(value = 100, message = "할인율은 100 이하여야 합니다.") Integer discountRate
) {
}
