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
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(DataFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
