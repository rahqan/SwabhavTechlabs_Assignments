package com.aurionpro.model;

import com.aurionpro.Interfaces.IFoodItem;

public class InvoicePrinter {

	public void print(Order order, int discountAmount, String paymentStatus, DeliveryPartner deliveryPartner) {
		System.out.println("\n========= INVOICE =========\n");

		System.out.println("Items Ordered:");
		for (OrderItem item : order.getItems()) {
			IFoodItem food = item.getItem();
			int qty = item.getQuantity();
			int price = food.getPrice();
			int total = item.getTotalPrice();

			System.out.printf("- %s (%s) x %d @ ₹%d = ₹%d%n", food.getName(), food.getCuisineType(), qty, price, total);
		}

		int totalAmount = order.getTotalAmount();
		int finalAmount = totalAmount - discountAmount;

		System.out.println("\n---------------------------");
		System.out.printf("Total Amount: ₹%d%n", totalAmount);
		System.out.printf("Discount Applied: ₹%d%n", discountAmount);
		System.out.printf("Amount to Pay: ₹%d%n", finalAmount);
		System.out.println("Payment Status: " + paymentStatus);

		if (deliveryPartner != null) {
			System.out.println("\nDelivered By:");
			System.out.println("Name: " + deliveryPartner.getName());
			System.out.println("Phone: " + deliveryPartner.getPhone());
		} else {
			System.out.println("\nNo delivery partner assigned.");
		}

		System.out.println("\n===========================\n");
	}
}
