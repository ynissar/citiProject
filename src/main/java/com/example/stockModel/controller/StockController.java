package com.example.stockModel.controller;

import com.example.stockModel.model.Stock;
import com.example.stockModel.model.Market;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
   
    private List<Stock> stockPrices = new ArrayList<>(Arrays.asList(
        new Stock("GOOG", "Google LLC", 1000.0, 1000.0, "Wed Mar 27 08:22:02 IST 2015", "Technology"),
        new Stock("AMZN", "Amazon.com, Inc.", 184.3, 184.0, "YYYY-MM-DD", "Technology"),
        new Stock("TSLA", "Telsa Inc.", 176.06, 174.60, "YYYY-MM-DD", "Technology"),
        new Stock("NDAQ", "Nasdaq Inc.", 58.97, 58.94, "YYYY-MM-DD", "Financial"),
        new Stock("BAYN", "Bayer AG", 27.48, 27.08, "YYYY-MM-DD", "Healthcare")
    ));
    // private Market TSX = new Market();
    
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
        throw new Error("Stock not found");
       
    }
    
    @PostMapping
    public Stock addStock(@RequestBody Stock newStock) {
        
        System.out.println(newStock.getTickerSymbol());
        stockPrices.add(newStock);
        return newStock;
    }

    @PutMapping("/index/{id}")
    public Stock editStock(@PathVariable String id, @RequestBody Stock editedStock) {
        for (Stock stock : stockPrices) {
            if (stock.getTickerSymbol().equals(id)) {
                stock.setTickerSymbol(editedStock.getTickerSymbol());
                stock.setCompanyName(editedStock.getCompanyName());
                stock.setClosingPrice(editedStock.getClosingPrice());
                stock.setOpeningPrice(editedStock.getOpeningPrice());
                stock.setDate(editedStock.getDate());
                stock.setIndustryGroup(editedStock.getIndustryGroup());
                return stock;
            }
        }
        return null;
    }
    

    @DeleteMapping("/index/{id}")
    public void deleteStock(@PathVariable String id) {
        for (Stock stock : stockPrices) {
            if (stock.getTickerSymbol().equals(id)) {
                stockPrices.remove(stock);
                System.out.println("Stock removed.");
                return;
            }
        }
        System.out.println("Stock could not be found");
        return;
    }
}