package com.study.board.coupon4;

public record CouponResponse(Long id, String name, String code, Integer discountRate) {

    public static CouponResponse from(Coupon coupon) {
        return new CouponResponse(coupon.getId(), coupon.getName(), coupon.getCode(), coupon.getDiscountRate());
    }
}
