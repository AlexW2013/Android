package com.alexw.retirementplanner;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by AlexW on 1/26/15.
 */
public class DataObject {

    @SerializedName("Symbol")
    public String symbol;

    @SerializedName("Date")
    public Date date;

    @SerializedName("Open")
    public String open;

    @SerializedName("High")
    public String high;

    @SerializedName("Low")
    public String low;

    @SerializedName("Close")
    public String close;

    @SerializedName("Volume")
    public String volume;

    @SerializedName("Adj_Close")
    public String adj_close;

    public DataObject(){

    }


}
