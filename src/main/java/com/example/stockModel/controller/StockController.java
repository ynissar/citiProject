package com.example.stockModel.controller;

import com.example.stockModel.model.Stock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Arrays;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
   
    private List<Stock> stockPrices = Arrays.asList(
        new Stock("GOOG", "Google LLC", 1000.0, 1000.0, "Wed Mar 27 08:22:02 IST 2015", "Technology"),
        new Stock("AMZN", "Amazon.com, Inc.", 184.3, 184.0, "YYYY-MM-DD", "Technology"),
        new Stock("TSLA", "Telsa Inc.", 176.06, 174.60, "YYYY-MM-DD", "Technology")
    );

    
    @GetMapping
    public List<Stock> getAllStockPrices() {
        return stockPrices;
    }

    @GetMapping("/index/{id}")
    public Stock getStockByTicker(@PathVariable String id){
        for (Stock stock : stockPrices) {
            if (id.equals(stock.getTickerSymbol())) {
                return stock;
            } 
        }
        System.out.println("Stock does not exist.");
        return null;
    }
}