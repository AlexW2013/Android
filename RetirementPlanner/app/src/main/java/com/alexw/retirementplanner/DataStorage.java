package com.alexw.retirementplanner;

import java.util.HashMap;

//Use a singleton class to keep all data values
public class DataStorage {

    private HashMap<String, Integer> values401k;
    private HashMap<String, Integer> valuesStocks;
    private HashMap<String, Integer> valuesOther;

    public void set401kData(String key, int value){
        values401k.put(key, value);
    }
    public int get401kData(String key){
        return values401k.get(key);
    }

    public void setStockData(String key, int value){
        valuesStocks.put(key, value);
    }
    public int getStockData(String key){
        return valuesStocks.get(key);
    }

    public void setOtherData(String key, int value){
        valuesOther.put(key, value);
    }
    public int getOtherData(String key){
        return valuesOther.get(key);
    }


    private static final DataStorage instance = new DataStorage();

    //Initialize all values to null
    private DataStorage(){
        values401k = new HashMap<>();
        values401k.put("balance401k", 0);
        values401k.put("income401k", 0);
        values401k.put("raise401k", 0);
        values401k.put("contrib401k", 0);
        values401k.put("interest401k", 0);

        valuesStocks = new HashMap<>();
        valuesStocks.put("balanceStocks", 0);
        valuesStocks.put("yearlyAddStocks", 0);
        valuesStocks.put("interestStocks", 0);

        valuesOther = new HashMap<>();
        valuesOther.put("balanceOther", 0);
        valuesOther.put("yearlyAddOther", 0);
        valuesOther.put("interestOther", 0);
    }

    //Return the instance
    public static DataStorage getInstance() {
        return instance;
    }

}
