package com.example.stockModel.model;

import java.util.List;

public class Market {
    private String name;
    private String country;
    private List<Security> stocks;

    public Market(){
        this.name = "TSX";
        this.country = "Canada";
    }

}