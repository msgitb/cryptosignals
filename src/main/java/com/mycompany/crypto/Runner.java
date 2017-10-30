/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto;

import java.io.IOException;
import java.util.logging.LogManager;

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
        System.out.print(r.returnTicker());
    }


    
            
}
