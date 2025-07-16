package com.aurionpro.model;

import java.util.List;
import java.util.Random;

import com.aurionpro.Interfaces.IDeliveryAssigner;

public class RandomDeliveryAssigner implements IDeliveryAssigner {
    private List<DeliveryPartner> partners;
    private Random random = new Random();

    public RandomDeliveryAssigner(List<DeliveryPartner> partners) {
        this.partners = partners;
    }

    @Override
    public DeliveryPartner assign() {
        if (partners.isEmpty()) return null;
        return partners.get(random.nextInt(partners.size()));
    }
}

