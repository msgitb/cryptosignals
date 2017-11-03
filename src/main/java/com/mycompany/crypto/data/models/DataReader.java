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
        while(true){
            
            try {
                synchronized(ds.getTickerInstances())
                {
                    ds.getTickerInstances().wait();
                }
                if(ds.tick>=0){
                Tick lastTick = ds.getTickerInstance(i).getTick("USDT_BTC");
                System.out.println(lastTick.last);
            }
            } catch (InterruptedException ex) {
                Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
