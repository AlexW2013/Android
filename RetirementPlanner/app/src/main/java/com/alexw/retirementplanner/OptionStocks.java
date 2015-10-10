package com.alexw.retirementplanner;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class OptionStocks extends Activity {
    private static boolean visited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_stocks);
        if (visited) {
            setOldValues();
        }
        visited = true;
    }

    //Set values for each field held in DataStorage
    public void setOldValues(){
        DataStorage dataHolder = DataStorage.getInstance();

        EditText balStocksText = (EditText) findViewById(R.id.bal_stocks);
        balStocksText.setText(Integer.toString(dataHolder.getStockData("balanceStocks")));

        EditText addStocksText = (EditText) findViewById(R.id.add_stocks);
        addStocksText.setText(Integer.toString(dataHolder.getStockData("yearlyAddStocks")));

        EditText growthStocksText = (EditText) findViewById(R.id.int_stocks);
        growthStocksText.setText(Integer.toString(dataHolder.getStockData("interestStocks")));
    }

    public void returnScreen(View view){
        //get the singleton instance that holds all data values
        DataStorage dataHolder = DataStorage.getInstance();

        //Retrieve values from editText Fields
        EditText balStocksText = (EditText) findViewById(R.id.bal_stocks);
        EditText addStocksText = (EditText) findViewById(R.id.add_stocks);
        EditText growthStocksText = (EditText) findViewById(R.id.int_stocks);

        // Exception checks - if any text field is blank, a popup will appear
        String balStocks = balStocksText.getText().toString();
        if(TextUtils.isEmpty(balStocks)){
            Toast.makeText(this, "Please enter a value for current 401k Balance!", Toast.LENGTH_SHORT).show();
            return;
        }

        String addStocks = addStocksText.getText().toString();
        if(TextUtils.isEmpty(addStocks)){
            Toast.makeText(this, "Please enter a value for current Income!", Toast.LENGTH_SHORT).show();
            return;
        }

        String growthStocks = growthStocksText.getText().toString();
        if(TextUtils.isEmpty(growthStocks)){
            Toast.makeText(this, "Please enter a value for expected Growth!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Set final values for stock page
        dataHolder.setStockData("balanceStocks", Integer.parseInt(balStocks));
        dataHolder.setStockData("yearlyAddStocks", Integer.parseInt(addStocks));
        dataHolder.setStockData("interestStocks", Integer.parseInt(growthStocks));

        //Return to main page
        finish();
    }
}