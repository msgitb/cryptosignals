/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.crypto.data.models.DataStore;
import com.mycompany.crypto.data.models.Tick;
import com.mycompany.crypto.data.models.TickerInstance;
import com.mycompany.crypto.data.models.TradingDirection;
import com.mycompany.crypto.data.models.Transaction;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class DataFetcher implements Runnable{

    private static final String PUBLIC_URL = "https://poloniex.com/public?";
    private final HTTPClient client;
    DataStore ds;
    
    public DataFetcher(DataStore ds){
        this.ds = ds;
        client = new HTTPClient();
    }
    
    public String returnTicker()
    {
        try
        {
            String url = PUBLIC_URL + "command=returnTicker";
            return client.getHttp(url, null);
        }
        catch (IOException ex)
        {
        }

        return null;
    }
    
    public String returnTradeHistory(String currencyPair, long start){
        start = (start==0) ?  (System.currentTimeMillis()/1000) - 1500: start; 
        try {
            String url = PUBLIC_URL + "command=returnTradeHistory&currencyPair=" + currencyPair + "&start=" + start + "&end=999999999999";
            return client.getHttp(url, null);
        }
        catch
            (IOException ex)
        {
        }
        return null;
    }
    
    @Override
    public void run() {
        Type type = new TypeToken<Map<String, Tick>>(){}.getType();
        Gson gson = new Gson();
        int i = 0;
        while(i<3)
        {
            System.out.println("Add ticker to dataStore...");
            ds.addTicker(new TickerInstance(gson.fromJson(returnTicker(), type)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DataFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;

        }
        String tradeHistory = returnTradeHistory("USDT_BTC", 0);
        Gson gson2 = new Gson();
        System.out.println("Direction= " + new TradingDirection(gson2.fromJson(tradeHistory, Transaction[].class)).direction());
    }
    
}
