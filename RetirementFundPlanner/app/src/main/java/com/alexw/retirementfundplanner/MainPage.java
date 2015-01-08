package com.alexw.retirementfundplanner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainPage extends ActionBarActivity {

    protected static int yearsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
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

    // Create 401k page when corresponding button is clicked
    public void button401k(View view){
        Intent intent401k = new Intent(this, Option401k.class);
        startActivity(intent401k);
    }

    // Create Stocks page when corresponding button is clicked
    public void buttonStocks(View view){
        Intent intentStocks = new Intent(this, OptionStocks.class);
        startActivity(intentStocks);
    }

    // Create Other investments page when corresponding button is clicked
    public void buttonOther(View view){
        Intent intentOther = new Intent(this, OptionOther.class);
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
        Intent finalIntent = new Intent(this, SummaryPage.class);
        startActivity(finalIntent);

    }
}
