package com.alexw.retirementplanner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class OptionStocks extends ActionBarActivity {

    protected static double stockBalance, stockAddition, stockInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_stocks);

        DataGrabber newGrabber = new DataGrabber();
        newGrabber.run();

        Double lowest = Double.parseDouble(newGrabber.lastQuote.close);

        TextView sp500View = (TextView) findViewById(R.id.yahoo_sp500);
        sp500View.setText(lowest.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option_stocks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void returnScreen(View view){

        //Retrieve values from editText Fields
        EditText balStocksText = (EditText) findViewById(R.id.bal_stocks);
        EditText addStocksText = (EditText) findViewById(R.id.add_stocks);
        EditText growthStocksText = (EditText) findViewById(R.id.int_stocks);

        // Exception checks - if any text field is blank, a popup will appear
        // Find a way to make this it's own method without crashing app

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
            Toast.makeText(this, "Please enter a value for expected Raise", Toast.LENGTH_SHORT).show();
            return;
        }



        //Set final values for stock page
        stockBalance = Double.parseDouble(balStocks);
        stockAddition = Double.parseDouble(addStocks);
        stockInterest = Double.parseDouble(growthStocks);


        //Return to main page
        finish();
    }
}