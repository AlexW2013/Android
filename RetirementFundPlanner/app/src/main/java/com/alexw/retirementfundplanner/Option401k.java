package com.alexw.retirementfundplanner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Option401k extends ActionBarActivity {

    public static double balance401k, income, raise, contribution, interestRate401k;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option401k);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option401k, menu);
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
        balance401k = Double.parseDouble(bal401k);
        income = Double.parseDouble(income401k);
        raise = Double.parseDouble(raise401k);
        contribution = Double.parseDouble(contrib401k);
        interestRate401k = Double.parseDouble(interest401k);

        // Return to the main page
        finish();

    }
}
