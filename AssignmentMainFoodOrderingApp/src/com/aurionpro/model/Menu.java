package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.Interfaces.IFoodItem;

public class Menu {
    private List<IFoodItem> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(IFoodItem item) {
        items.add(item);
    }

    public boolean removeItemById(int id) {
        return items.removeIf(item -> item.getId() == id);
    }


    public IFoodItem getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public IFoodItem getItemById(int id) {
        for (IFoodItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void displayAllItems() {
        System.out.println("\n------ FULL MENU ------");
        for (IFoodItem item : items) {
            System.out.printf("[%d] %s - %s - ₹%d%n",
                    item.getId(), item.getName(), item.getCuisineType(), item.getPrice());
        }
    }

    public void displayByCuisine(String cuisine) {
        System.out.println("\n------ " + cuisine.toUpperCase() + " MENU ------");
        boolean found = false;
        for (IFoodItem item : items) {
            if (item.getCuisineType().equalsIgnoreCase(cuisine)) {
                System.out.printf("[%d] %s - ₹%d%n", item.getId(), item.getName(), item.getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found for this cuisine.");
        }
    }

    public int getNextId() {
        if (items.isEmpty()) return 1;
        return items.get(items.size() - 1).getId() + 1;
    }

    // ADD THIS:
    public void initialize() {
        addItem(new FoodItem(getNextId(), "Paneer Butter Masala", "Indian", 250));
        addItem(new FoodItem(getNextId(), "Margherita Pizza", "Italian", 300));
        addItem(new FoodItem(getNextId(), "Sushi Roll", "Japanese", 400));
        addItem(new FoodItem(getNextId(), "Tandoori Chicken", "Indian", 350));
        addItem(new FoodItem(getNextId(), "Spaghetti Carbonara", "Italian", 320));
        addItem(new FoodItem(getNextId(), "Ramen Bowl", "Japanese", 380));
        addItem(new FoodItem(getNextId(), "Chole Bhature", "Indian", 200));
        addItem(new FoodItem(getNextId(), "Tempura Prawns", "Japanese", 450));
    }


    public List<IFoodItem> getItems() {
        return items;
    }
}
