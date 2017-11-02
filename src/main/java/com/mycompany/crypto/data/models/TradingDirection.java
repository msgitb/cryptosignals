/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto.data.models;

import java.util.ArrayList;

/**
 *
 * @author marcelo
 */
public class TradingDirection {
    double totalBuy;
    double totalSell;
    double direction;
    ArrayList<Transaction> trxs = new ArrayList<>();

    public TradingDirection(Transaction[] txs) {
        System.out.println("Got " + txs.length + " transactions");
        addTransaction(txs);
        calculateDirection();
    }
    
    public double direction() {
        return this.direction;
    }
    
    public void addTransaction(Transaction[] trxs)
    {
        for (Transaction trx : trxs) {
            switch (trx.getType()) {
                case "sell":
                    this.totalSell += trx.amount;
                    break;
                case "buy":
                    this.totalBuy += trx.amount;
                    break;
                default:
                    break;
            }
            this.trxs.add(trx);
        }
    }

    private void calculateDirection() {
        direction = totalSell - totalBuy;
    }
    
    
}
