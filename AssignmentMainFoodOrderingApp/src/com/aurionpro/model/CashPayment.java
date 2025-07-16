package com.aurionpro.model;

import com.aurionpro.Interfaces.IPayment;

public class CashPayment implements IPayment {
    @Override
    public String getPayment(int amount) {
        return "Cash payment received: ₹" + amount;
    }

	
}
