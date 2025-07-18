package com.aurionpro.model;

public enum CellValue {
    EMPTY(" "), X("X"), O("O");

    private String symbol;

    CellValue(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}