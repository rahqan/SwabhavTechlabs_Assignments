package com.aurionpro.model;

import com.aurionpro.Interfaces.IFoodItem;

public class OrderItem {
    private IFoodItem item;
    private int quantity;

    public OrderItem(IFoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public OrderItem(OrderItem original) {
        this.item = original.getItem(); 
        this.quantity = original.getQuantity();
    }


    public IFoodItem getItem() {
        return item;
    }
    public void setQuantity(int quantity) {
    	this.quantity=quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return item.getPrice() * quantity;
    }
}
