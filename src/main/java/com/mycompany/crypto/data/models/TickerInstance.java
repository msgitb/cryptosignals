package com.mycompany.crypto.data.models;

import java.sql.Timestamp;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
    public class TickerInstance {

        Map<String, Tick> tickerResults;
        private final Timestamp ts;

        public TickerInstance(Map<String, Tick> tickerResults) {
            this.tickerResults = tickerResults;
            this.ts = new Timestamp(System.currentTimeMillis());
            ts.getTime();
        }

    public Tick getTick(String key) {
        return tickerResults.get(key);
    }
    
    public String timestamp(){
        return ts.toString();
    }
    
    public long getUnixTime(){
        return ts.getTime()/1000;
    }
 
        
    }