/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto.data.models;

import com.google.gson.Gson;

/**
 *
 * @author marcelo
 */
public class Transaction {
    int globalTradeID;
    int tradeID;
    String date;
    String type;
    double rate;
    double amount;
    double total; 

    public Transaction(int globalTradeID, int tradeID, String date, String type, double rate, double amount, double total) {
        this.globalTradeID = globalTradeID;
        this.tradeID = tradeID;
        this.date = date;
        this.type = type;
        this.rate = rate;
        this.amount = amount;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
    
    
    @Override
    public String toString()
    {

        return new Gson().toJson(this);
    }
    
}
