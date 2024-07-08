package com.example.stockModel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Market {
    private String name;
    private String region;
    private List<Stock> stockPrices;

    public Market(String name, String region){
        this.name = "TSX";
        this.region = "NAM";
        this.stockPrices = new ArrayList<>(Arrays.asList(
            new Stock("GOOG", "Google LLC", 1000.0, 1000.0, "Wed Mar 27 08:22:02 GMT-0400 (Eastern Daylight Time)", "Technology"),
            new Stock("AMZN", "Amazon.com, Inc.", 184.3, 184.0, "Mon Jul 8 08:22:02 GMT-0400 (Eastern Daylight Time)", "Technology"),
            new Stock("TSLA", "Telsa Inc.", 176.06, 174.60, "Tues Jun 27 08:22:02 GMT-0400 (Eastern Daylight Time)", "Technology"),
            new Stock("NDAQ", "Nasdaq Inc.", 58.97, 58.94, "Thurs May 7 08:22:02 GMT-0400 (Eastern Daylight Time)", "Financial"),
            new Stock("BAYN", "Bayer AG", 27.48, 27.08, "Fri Aug 23 08:22:02 GMT-0400 (Eastern Daylight Time)", "Healthcare")
        ));
    }

    public String getName() {
        return this.name;
    }

    public String getRegion() {
        return this.region;
    }

    public List<Stock> getStockPrices() {
        return this.stockPrices;
    }

}