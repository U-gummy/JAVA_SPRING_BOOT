package com.study.board.coupon4;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public ResponseEntity<List<CouponResponse>> list() {
        List<CouponResponse> responses = couponService.list();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> detail(@PathVariable Long id) {
        CouponResponse response = couponService.detail(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CouponResponse> create(@Valid @RequestBody CouponCreateRequest request) {
        CouponResponse response = couponService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> update(@PathVariable Long id, @Valid @RequestBody CouponUpdateRequest request) {
        CouponResponse response = couponService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        couponService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
