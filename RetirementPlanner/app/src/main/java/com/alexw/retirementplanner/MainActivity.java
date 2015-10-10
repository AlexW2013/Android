package com.alexw.retirementplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    protected static double yearsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Create 401k page when corresponding button is clicked
    public void button401k(View view){
        Intent intent401k = new Intent(getApplicationContext(), Option401k.class);
        startActivity(intent401k);
    }

    // Create Stocks page when corresponding button is clicked
    public void buttonStocks(View view){
        Intent intentStocks = new Intent(getApplicationContext(), OptionStocks.class);
        startActivity(intentStocks);
    }

    // Create Other investments page when corresponding button is clicked
    public void buttonOther(View view){
        Intent intentOther = new Intent(getApplicationContext(), OptionOther.class);
        startActivity(intentOther);
    }

    // Create the final summary page
    public void buttonFinal(View view) {

        // Check if years is empty, and create toast if so
        String yearsEntry = ((EditText) findViewById(R.id.yearsMain)).getText().toString();

        if(TextUtils.isEmpty(yearsEntry)){
            Toast.makeText(this, "Please enter a value for Years until Retirement!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get data for years until retirement, based on entry
        yearsLeft = Integer.parseInt(((EditText) findViewById(R.id.yearsMain)).getText().toString());

        // Create the final page
        Intent finalIntent = new Intent(getApplicationContext(), SummaryPage.class);
        startActivity(finalIntent);

    }
}
