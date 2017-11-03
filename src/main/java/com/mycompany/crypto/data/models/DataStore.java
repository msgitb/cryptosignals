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
public class DataStore {
    
    int transaction = 0;
    int tick = 0;
    
    private ArrayList<TickerInstance> instances;
    private ArrayList<TradingDirection> directions;
    
    public DataStore(){
        instances = new ArrayList<TickerInstance>();
        directions = new ArrayList<TradingDirection>();
        
    }
    
    public void addTicker(TickerInstance i){
        instances.add(i);
        synchronized(instances){
            tick++;
            instances.notifyAll();
        }
        
    }

    public void addTradingDirection(TradingDirection td){
        directions.add(td);
        synchronized(directions){
            directions.notifyAll();
            transaction++;
        }
    }
    
    public TradingDirection getTradingDirection(int index){
        return directions.get(index);
    }
   
    public ArrayList<TickerInstance> getTickerInstances()
    {
        return instances;
    }
            
    public TickerInstance getTickerInstance(int index)
    {
        return instances.get(index);
    }
    
    



}
