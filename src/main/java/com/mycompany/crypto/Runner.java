/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.mycompany.crypto.data.models.Ticker;
import java.util.Map;

/**
 *
 * @author marcelo
 */
public class Runner {
    private static final String PUBLIC_URL = "https://poloniex.com/public?";
    private HTTPClient client;
    public Runner()
    {
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
    
    public static void main(String[] args){    
        Runner r = new Runner();
        System.out.println(r.returnTicker());
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Gson gson;
    
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
            {
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DTF);
            }
            
        }).create(); 
    
        Type mapOfTicks = new TypeToken<Map<String, Ticker>>(){}.getType();
        Map<String, Ticker> tickerResults = gson.fromJson(r.returnTicker(), mapOfTicks);
        System.out.println("Result: " +gson.toJson(tickerResults.get("USDT_BTC")));
     } 


    
            
}
