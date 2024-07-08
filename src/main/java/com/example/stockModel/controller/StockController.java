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

    private Market TSX = new Market("TSX", "NAM");
    
    @GetMapping
    public List<Stock> getAllStockPrices() {
        return TSX.getStockPrices();
    }

    @GetMapping("/market")
    public String getMarket() {
        return TSX.getName();
    }

    @GetMapping("/index/{id}")
    public Stock getStockByTicker(@PathVariable String id){
        for (Stock stock : TSX.getStockPrices()) {
            if (id.equals(stock.getTickerSymbol())) {
                return stock;
            } 
        }
        throw new Error("Stock not found");
       
    }
    
    @PostMapping
    public Stock addStock(@RequestBody Stock newStock) {
        System.out.println(newStock.getTickerSymbol());
        TSX.getStockPrices().add(newStock);
        return newStock;
    }

    @PutMapping("/index/{id}")
    public Stock editStock(@PathVariable String id, @RequestBody Stock editedStock) {
        for (Stock stock : TSX.getStockPrices()) {
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
        for (Stock stock : TSX.getStockPrices()) {
            if (stock.getTickerSymbol().equals(id)) {
                TSX.getStockPrices().remove(stock);
                System.out.println("Stock removed.");
                return;
            }
        }
        System.out.println("Stock could not be found");
        return;
    }
}