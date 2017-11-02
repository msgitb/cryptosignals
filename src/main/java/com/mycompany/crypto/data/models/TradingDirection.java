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
    ArrayList<Transaction> trxs;

    public TradingDirection(Transaction[] txs) {
        for(int i=0; i<txs.length; i++)
        {
            Transaction t = txs[i];
            switch (t.getType())
            {
                case "sell":
                    totalSell +=t.amount;
                    break;
                case "buy":
                    totalBuy += t.amount;
                    break;
                default:
                    break;
            }
        }
        if(totalBuy>=totalSell)
        {
            direction=-totalBuy/totalSell;
        }
        else
        {
            direction=totalSell/totalBuy;
        }
        System.out.println("Got " + txs.length + " transactions");
    }
    
    public double direction() {
        return this.direction;
    }
    
    
    
    
}
