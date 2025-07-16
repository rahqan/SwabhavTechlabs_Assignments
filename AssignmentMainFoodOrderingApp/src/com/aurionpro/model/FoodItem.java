package com.aurionpro.model;


import com.aurionpro.Interfaces.IFoodItem;

public class FoodItem implements IFoodItem {
    private int id;
    private String name;
    private String cuisineType;
    private int price;

    public FoodItem(int id, String name, String cuisineType, int price) {
        this.id = id;
        this.name = name;
        this.cuisineType = cuisineType;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCuisineType() {
        return cuisineType;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

