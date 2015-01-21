package com.alexw.retirementplanner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;


public class SummaryPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        // Calculate final value of 401k, compounded yearly
        double finalVal401k = Option401k.balance401k;
        double newIncome = Option401k.income;
        double interest401k = (1.0 + (Option401k.interestRate401k)/100);
        double contribution = newIncome * (Option401k.contribution/100);

        // Formula for calculating 401k value
        for(int i = 0; i < MainActivity.yearsLeft; i++){
            finalVal401k = contribution + (interest401k * (finalVal401k));
            newIncome = newIncome * (1.0 + Option401k.raise/100);
            contribution = newIncome * (Option401k.contribution/100);
        }

        // Format String like money is normally represented
        DecimalFormat dollarCent = new DecimalFormat( "###,###,###,###.00");

        // Set textView field to 401k Val
        TextView value401k = (TextView) findViewById(R.id.final401k);
        value401k.setText("Your 401k is valued at: $" + dollarCent.format(finalVal401k));

        // Calculate and display the final value of Stocks
        double finalValStocks = OptionStocks.stockBalance;
        double interestStocks = (1.0 + OptionStocks.stockInterest/100);
        double newStocks = OptionStocks.stockAddition;
        for(int i = 0; i < MainActivity.yearsLeft; i++) {
            finalValStocks = (finalValStocks + newStocks) * interestStocks;
        }

        TextView valueStocks = (TextView) findViewById(R.id.finalStocks);
        valueStocks.setText("Your combined Stocks are valued at: $" + dollarCent.format(finalValStocks));


        // Calculate and display the final value of Other Investments
        double finalValOther = OptionOther.otherBalance;
        double interestOther = (1.0 + OptionOther.otherInterest/100);
        double newOther = OptionOther.otherAddition;
        for(int i = 0; i < MainActivity.yearsLeft; i++) {
            finalValOther = (finalValOther + newOther) * interestOther;
        }

        TextView valueOther = (TextView) findViewById(R.id.finalOther);
        valueOther.setText("Your combined Additional Investments are valued at: $" + dollarCent.format(finalValOther));


        // Display the value of all three combined
        double finalValue = finalVal401k + finalValOther + finalValStocks;
        TextView totalValue = (TextView) findViewById(R.id.finalVal);
        totalValue.setText("Your combined total retirement fund is valued at: $" + dollarCent.format(finalValue));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_page, menu);
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
}
