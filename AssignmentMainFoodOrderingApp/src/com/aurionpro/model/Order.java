package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items ;
    
    // refactor to add discount in order itself so its better code in admin to get orderHistory with discount
    // currently we calculate the discount again..thats bad

    public void addItem(OrderItem newItem) {
        for (OrderItem item : items) {
            if (item.getItem().equals(newItem.getItem())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }
    
    public Order(Order original) {
        this.items = new ArrayList<>();
        for (OrderItem item : original.getItems()) {
            this.items.add(new OrderItem(item)); 
        }
    }
    public Order() {
    	items= new ArrayList<>();
    }

    public List<OrderItem> getItems() {
        return items;
    }


    public int getTotalAmount() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
