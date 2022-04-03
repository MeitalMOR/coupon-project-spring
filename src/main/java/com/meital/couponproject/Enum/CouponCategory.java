package com.meital.couponproject.Enum;

public enum CouponCategory {
    FOOD(1),
    ELECTRICITY(2),
    RESTAURANT(3),
    VACATION(4),
    SPA_AND_BEAUTY(5),
    THINGS_TO_DO_CHILDREN(6),
    HOME_DECOR(7);

    private final long id;

    private CouponCategory(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
