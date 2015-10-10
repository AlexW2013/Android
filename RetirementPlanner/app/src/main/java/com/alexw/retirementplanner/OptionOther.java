package com.alexw.retirementplanner;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class OptionOther extends Activity {
    private static boolean visited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_other);
        if (visited) {
            setOldValues();
        }
        visited = true;
    }

    //Set values for each field held in DataStorage
    public void setOldValues(){
        DataStorage dataHolder = DataStorage.getInstance();

        EditText balOtherText = (EditText) findViewById(R.id.bal_other);
        balOtherText.setText(Integer.toString(dataHolder.getOtherData("balanceOther")));

        EditText addOtherText = (EditText) findViewById(R.id.add_other);
        addOtherText.setText(Integer.toString(dataHolder.getOtherData("yearlyAddOther")));

        EditText growthOtherText = (EditText) findViewById(R.id.int_other);
        growthOtherText.setText(Integer.toString(dataHolder.getOtherData("interestOther")));
    }

    public void returnScreen(View view){
        //get the singleton instance that holds all data values
        DataStorage dataHolder = DataStorage.getInstance();

        //Retrieve values from editText Fields
        EditText balOtherText = (EditText) findViewById(R.id.bal_other);
        EditText addOtherText = (EditText) findViewById(R.id.add_other);
        EditText growthOtherText = (EditText) findViewById(R.id.int_other);

        // Exception checks - if any text field is blank, a popup will appear
        String balOther = balOtherText.getText().toString();
        if(TextUtils.isEmpty(balOther)){
            Toast.makeText(this, "Please enter a value for current 401k Balance!", Toast.LENGTH_SHORT).show();
            return;
        }

        String addOther = addOtherText.getText().toString();
        if(TextUtils.isEmpty(addOther)){
            Toast.makeText(this, "Please enter a value for current Income!", Toast.LENGTH_SHORT).show();
            return;
        }

        String growthOther = growthOtherText.getText().toString();
        if(TextUtils.isEmpty(growthOther)){
            Toast.makeText(this, "Please enter a value for expected Growth!", Toast.LENGTH_SHORT).show();
            return;
        }


        //Set final values for Other page
        dataHolder.setOtherData("balanceOther", Integer.parseInt(balOther));
        dataHolder.setOtherData("yearlyAddOther", Integer.parseInt(addOther));
        dataHolder.setOtherData("interestOther", Integer.parseInt(growthOther));

        //Return to main page
        finish();
    }

}