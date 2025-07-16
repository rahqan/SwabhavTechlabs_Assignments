package com.aurionpro.model;

import com.aurionpro.Interfaces.IDiscountCalculator;

public class FlatDiscount implements IDiscountCalculator {
    private int flatAmount;

    public FlatDiscount(int flatAmount) {
        this.flatAmount = flatAmount;
    }

    @Override
    public int calculateDiscount(Order order) {
        if (order.getTotalAmount() > flatAmount) {
            return 100;
        }
        return 0;
    }
}

