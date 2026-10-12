package com.study.board.coupon4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Integer discountRate;

    protected Coupon() {
    }

    public Coupon(String name, String code, Integer discountRate) {
        this.name = name;
        this.code = code;
        this.discountRate = discountRate;
    }

    public void update(String name, String code, Integer discountRate) {
        this.name = name;
        this.code = code;
        this.discountRate = discountRate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
 
    public String getCode() {
        return code;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }
}
