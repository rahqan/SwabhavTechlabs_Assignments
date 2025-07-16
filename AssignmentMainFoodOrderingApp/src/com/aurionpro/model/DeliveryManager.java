package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeliveryManager {
    private List<DeliveryPartner> partners;

    public DeliveryManager() {
        partners = new ArrayList<>();
    }

    public void addPartner(DeliveryPartner partner) {
        partners.add(partner);
    }

    public boolean removePartnerById(int id) {
        return partners.removeIf(p -> p.getId() == id);
    }


    public DeliveryPartner getRandomDeliveryPartner() {
        if (partners.isEmpty()) return null;
        Random rand = new Random();
        return partners.get(rand.nextInt(partners.size()));
    }

    public void displayPartners() {
        System.out.println("\n-- Delivery Partners --");
        for (DeliveryPartner p : partners) {
            System.out.printf("[%d] %s - %s%n", p.getId(), p.getName(), p.getPhone());
        }
    }


    public int getNextId() {
        if (partners.isEmpty()) return 1;
        return partners.get(partners.size() - 1).getId() + 1;
    }

    // ADD THIS:
    public void initialize() {
        addPartner(new DeliveryPartner(getNextId(), "Ravi Kumar","1234567890"));
        addPartner(new DeliveryPartner(getNextId(), "Sneha Singh","0987654321"));
        addPartner(new DeliveryPartner(getNextId(), "Ajay Patel","6758493021"));
    }

    public List<DeliveryPartner> getPartners() {
        return partners;
    }
}
