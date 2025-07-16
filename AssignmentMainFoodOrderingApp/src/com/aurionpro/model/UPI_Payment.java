package com.aurionpro.model;

import com.aurionpro.Interfaces.IPayment;


public class UPI_Payment implements IPayment {
	
	@Override
	    public String getPayment(int amount) {
		return "UPI payment received: ₹" + amount;

	    }
}

