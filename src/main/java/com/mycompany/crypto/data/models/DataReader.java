/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crypto.data.models;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class DataReader implements Runnable{

    DataStore ds;
    public DataReader(DataStore ds) {
        this.ds = ds;
    }

    
    @Override
    public void run() {
        int i = 0;
        int direction = 0;
        
        while(true){
            
            try {
                synchronized(ds)
                {
                    ds.wait();
                
                if(ds.tick>=0){
                    ds.tick--;
                    Tick lastTick = ds.getTickerInstance(i).getTick("USDT_BTC");
                    System.out.println(i + " " + lastTick.last);
                    i++;
                }
                if(ds.transaction>0)
                {
                    System.out.println("Direction= " + ds.getTradingDirection(direction).direction());
                    direction++;  
                    ds.transaction--;      
                }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
