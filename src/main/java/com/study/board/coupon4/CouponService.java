package com.study.board.coupon4;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<CouponResponse> list() {
        return couponRepository.findAll().stream()
                .map(CouponResponse::from)
                .toList();
    }

    public CouponResponse detail(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));

        return CouponResponse.from(coupon);
    }


    public CouponResponse create(CouponCreateRequest request) {
        Coupon coupon = new Coupon(request.name(), request.code(), request.discountRate());

        Coupon saved = couponRepository.save(coupon);

        return CouponResponse.from(saved);
    }


    @Transactional
    public CouponResponse update(Long id, CouponUpdateRequest request) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));

        coupon.update(request.name(), request.code(), request.discountRate());

        return CouponResponse.from(coupon);

    }

    @Transactional
    public void delete(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));

        couponRepository.delete(coupon);
    }

}
