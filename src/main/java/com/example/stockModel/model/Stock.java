package com.example.stockModel.model;

// import java.util.String;

public class Stock {
    
    private String tickerSymbol;
    private String companyName;
    private double closingPrice;
    private double openingPrice;
    private String date;
    private String industryGroup;
    
    public Stock(String tickerSymbol, String companyName, double closingPrice, double openingPrice, String String, String industryGroup) {
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
        this.closingPrice = closingPrice;
        this.openingPrice = openingPrice;
        this.date = String;
        this.industryGroup = industryGroup;
    }
    
    public String getTickerSymbol() { return this.tickerSymbol; }
    public String getCompanyName() { return this.companyName; }
    public double getClosingPrice() { return this.closingPrice; }
    public double getOpeningPrice() { return this.openingPrice; }
    public String getDate() { return this.date; }
    public String getIndustryGroup() { return this.industryGroup; }

    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setClosingPrice(double closingPrice) { this.closingPrice = closingPrice; }
    public void setOpeningPrice(double openingPrice) { this.openingPrice = openingPrice; }
    public void setDate(String date) { this.date = date; }
    public void setIndustryGroup(String industryGroup) { this.industryGroup = industryGroup; }

}