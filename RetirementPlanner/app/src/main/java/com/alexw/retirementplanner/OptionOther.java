package com.alexw.retirementplanner;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class OptionOther extends ActionBarActivity {

    protected static double otherBalance, otherAddition, otherInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_other);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option_other, menu);
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


        //Set final values for stock page
        otherBalance = Integer.parseInt(balOther);
        otherAddition = Integer.parseInt(addOther);
        otherInterest = Integer.parseInt(growthOther);

        //Return to main page
        finish();
    }

}