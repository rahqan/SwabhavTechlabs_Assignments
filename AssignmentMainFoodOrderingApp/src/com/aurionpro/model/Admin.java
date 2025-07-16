package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.Interfaces.IDiscountCalculator;
import com.aurionpro.Interfaces.IFoodItem;

public class Admin {
    private final List<Order> orderHistory = new ArrayList<>();

    public void addFoodItem(Menu menu, IFoodItem item) {
        menu.addItem(item);
        System.out.println("Food item added: " + item.getName() + " (ID: " + item.getId() + ")");
    }

    public void removeFoodItemById(Menu menu, int itemId) {
        boolean removed = menu.removeItemById(itemId);
        if (removed) {
            System.out.println("Food item with ID " + itemId + " removed.");
        } else {
            System.out.println("No food item found with ID " + itemId);
        }
    }

    public void addDeliveryPartner(DeliveryManager manager, DeliveryPartner partner) {
        manager.addPartner(partner);
        System.out.println("Delivery partner added: " + partner.getName() + " (ID: " + partner.getId() + ")");
    }

    public void removeDeliveryPartnerById(DeliveryManager manager, int partnerId) {
        boolean removed = manager.removePartnerById(partnerId);
        if (removed) {
            System.out.println("Delivery partner with ID " + partnerId + " removed.");
        } else {
            System.out.println("No delivery partner found with ID " + partnerId);
        }
    }

    public void recordOrder(Order order) {
     
    	  Order copiedOrder = new Order(order);  
    	    orderHistory.add(copiedOrder);
    }

    public void viewAllOrders(IDiscountCalculator discCalculator) {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }

        System.out.println("\n====== ORDER HISTORY ======");
        int count = 1;
        for (Order order : orderHistory) {
            System.out.println("Order #" + count++);
            for (OrderItem item : order.getItems()) {
                System.out.printf("- %s x%d @ ₹%d = ₹%d%n",
                        item.getItem().getName(),
                        item.getQuantity(),
                        item.getItem().getPrice(),
                        item.getTotalPrice());
            }

            int total = order.getTotalAmount();
            if (total > discCalculator.calculateDiscount(order)) {
                System.out.println("Subtotal: ₹" + total);
                System.out.println("Discount: ₹100");
                total -= 100;
            }

            System.out.println("Total: ₹" + total);
            System.out.println("----------------------------");
        }
    }

}
