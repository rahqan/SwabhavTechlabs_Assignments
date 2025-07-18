package com.aurionpro.model;

class Player {
    private String name;
    private CellValue symbol;

    public Player(String name, CellValue symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public CellValue getSymbol() {
        return symbol;
    }
}
