package com.alexw.retirementplanner;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class Option401k extends Activity {
    private static boolean visited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option401k);
        //If the page is being visited for the first time, editText fields don't need to be changed
        if (visited) {
            setOldValues();
        }
        visited = true;
    }

    //Set values for each field held in DataStorage
    public void setOldValues(){
        DataStorage dataHolder = DataStorage.getInstance();

        EditText bal401kText = (EditText) findViewById(R.id.bal_401k);
        bal401kText.setText(Integer.toString(dataHolder.get401kData("balance401k")));

        EditText income401kText = (EditText) findViewById(R.id.income_401k);
        income401kText.setText(Integer.toString(dataHolder.get401kData("income401k")));

        EditText raise401kText = (EditText) findViewById(R.id.raise_401k);
        raise401kText.setText(Integer.toString(dataHolder.get401kData("raise401k")));

        EditText contrib401kText = (EditText) findViewById(R.id.contrib_401k);
        contrib401kText.setText(Integer.toString(dataHolder.get401kData("contrib401k")));

        EditText interest401kText = (EditText) findViewById(R.id.interest_401k);
        interest401kText.setText(Integer.toString(dataHolder.get401kData("interest401k")));

    }

    public void returnScreen(View view){
        //get the singleton instance that holds all data values
        DataStorage dataHolder = DataStorage.getInstance();

        // Retrieve values from editText fields
        EditText bal401kText = (EditText) findViewById(R.id.bal_401k);
        EditText income401kText = (EditText) findViewById(R.id.income_401k);
        EditText raise401kText = (EditText) findViewById(R.id.raise_401k);
        EditText contrib401kText = (EditText) findViewById(R.id.contrib_401k);
        EditText interest401kText = (EditText) findViewById(R.id.interest_401k);


        // Exception checks - if any text field is blank, a popup will appear
        String bal401k = bal401kText.getText().toString();
        if(TextUtils.isEmpty(bal401k)){
            Toast.makeText(this, "Please enter a value for current 401k Balance!", Toast.LENGTH_SHORT).show();
            return;
        }

        String income401k = income401kText.getText().toString();
        if(TextUtils.isEmpty(income401k)){
            Toast.makeText(this, "Please enter a value for current Income!", Toast.LENGTH_SHORT).show();
            return;
        }

        String raise401k = raise401kText.getText().toString();
        if(TextUtils.isEmpty(raise401k)){
            Toast.makeText(this, "Please enter a value for expected Raise", Toast.LENGTH_SHORT).show();
            return;
        }

        String contrib401k = contrib401kText.getText().toString();
        if(TextUtils.isEmpty(contrib401k)){
            Toast.makeText(this, "Please enter a value for expected Contribution!", Toast.LENGTH_SHORT).show();
            return;
        }

        String interest401k = interest401kText.getText().toString();
        if(TextUtils.isEmpty(interest401k)){
            Toast.makeText(this, "Please enter a value for expected Interest!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set values for 401k page
        dataHolder.set401kData("balance401k", Integer.parseInt(bal401k));
        dataHolder.set401kData("income401k", Integer.parseInt(income401k));
        dataHolder.set401kData("raise401k", Integer.parseInt(raise401k));
        dataHolder.set401kData("contrib401k", Integer.parseInt(contrib401k));
        dataHolder.set401kData("interest401k", Integer.parseInt(interest401k));

        // Return to the main page
        finish();

    }
}
