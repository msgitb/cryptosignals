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
    
    
    private ArrayList<TickerInstance> instances;
    
    public DataStore(){
        instances = new ArrayList<TickerInstance>();
        
    }
    
    public void addTicker(TickerInstance i){
        instances.add(i);
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
