package com.example.stockModel.model;

public abstract class Security {

    protected double closingPrice;
    protected double openingPrice;
    protected String date;
    protected String type;

    public abstract double getClosingPrice();
    public abstract double getOpeningPrice();
    public abstract String getDate();

    @Override
    public String toString(){
        return String.format("Security: %s\nDate: %s\nOpening Price: %.2f\nClosing Price: %.2f\n", type, date, openingPrice, closingPrice);
    }
    
}