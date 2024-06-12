package com.example.stockModel.model;

public class Bond extends Security {
    private double coupon;

    public Bond(double closingPrice, double openingPrice, String String) {
        this.closingPrice = closingPrice;
        this.openingPrice = openingPrice;
        this.date = String;
        this.type = "Bond";
    }

    public double getClosingPrice() { return this.closingPrice; }
    public double getOpeningPrice() { return this.openingPrice; }
    public String getDate() { return this.date; }
    public double getCoupon() { return coupon; }

    public void setClosingPrice(double closingPrice) { this.closingPrice = closingPrice; }
    public void setOpeningPrice(double openingPrice) { this.openingPrice = openingPrice; }
    public void setDate(String date) { this.date = date; }
    public void setCoupon(double coupon) { this.coupon = coupon; }

    public double calculateDividendYield() {
        return coupon / (getClosingPrice() + getOpeningPrice() / 2);
    }
}