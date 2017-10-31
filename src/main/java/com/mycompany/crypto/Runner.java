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
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.mycompany.crypto.data.models.DataStore;
import com.mycompany.crypto.data.models.TickerInstance;
import com.mycompany.crypto.data.models.Tick;
import java.util.Map;

/**
 *
 * @author marcelo
 */
public class Runner {
    private static final String PUBLIC_URL = "https://poloniex.com/public?";
    private final HTTPClient client;
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
    
    private String getChartData(String currencyPair, String startEpochInSec, String endEpochInSec, String periodInSec)
    {
        try
        {
            String url = PUBLIC_URL + "command=returnChartData&currencyPair=" + currencyPair + "&start=" + startEpochInSec + "&end=" + endEpochInSec + "&period=" + periodInSec;
            return client.getHttp(url, null);
        }
        catch (IOException ex)
        {
            System.out.println("Call to Chart Data API resulted in exception - " + ex.getMessage());
        }

        return null;
    }

    
    public static void main(String[] args){    
        Runner r = new Runner();
        System.out.println(r.returnTicker());
        Gson gson;
        
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
            {
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DTF);
            }
          
            
        }); 
        
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {

            @Override
            public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.toString());
            }
            
        });
         gson = gsonBuilder.create();
    
        Type type = new TypeToken<Map<String, Tick>>(){}.getType();
        DataStore d = new DataStore();
        d.addTicker(new TickerInstance(gson.fromJson(r.returnTicker(), type)));
        System.out.println(gson.toJson(d.getTickerInstance(0).getTick("USDT_BTC")));
        System.out.println(d.getTickerInstance(0).getUnixTime());
     } 


    
            
}
