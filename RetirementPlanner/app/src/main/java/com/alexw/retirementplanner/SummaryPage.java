package com.alexw.retirementplanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;


public class SummaryPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        //Display individual final values for each of the three investment accounts
        double final401k = display401kInfo();
        double finalStock = displayStockInfo();
        double finalOther = displayOtherInfo();

        //Display the final total value of all combined investments
        displayTotalValue(final401k, finalStock, finalOther);

    }

    double display401kInfo(){
        DataStorage dataHolder = DataStorage.getInstance();

        // Format String like money is normally represented
        DecimalFormat dollarCent = new DecimalFormat( "###,###,###,###,###.00");

        // Calculate final value of 401k, compounded yearly
        double balance401k = (double) dataHolder.get401kData("balance401k");
        double newIncome = (double) dataHolder.get401kData("income401k");
        double interest401k = (1.0 + (dataHolder.get401kData("interest401k"))/100.0);
        double contribution = (dataHolder.get401kData("contrib401k")/100.0);

        double raise = (double) dataHolder.get401kData("raise401k");

        // Formula for calculating 401k value
        for(int i = 0; i < MainActivity.yearsLeft; i++){
            balance401k = (contribution * newIncome) + (interest401k * balance401k);
            newIncome = newIncome * (1.0 + raise/100);
        }

        // Set textView field to 401k Val
        TextView value401k = (TextView) findViewById(R.id.final401k);
        value401k.setText("Your 401k is valued at: $" + dollarCent.format(balance401k));

        return balance401k;

    }

    double displayStockInfo(){
        DataStorage dataHolder = DataStorage.getInstance();

        DecimalFormat dollarCent = new DecimalFormat( "###,###,###,###.00");

        double finalValStocks = (double) dataHolder.getStockData("balanceStocks");
        double interestStocks = (1.0 + dataHolder.getStockData("interestStocks")/100.0);
        double newStocks = (double) dataHolder.getStockData("yearlyAddStocks");

        for(int i = 0; i < MainActivity.yearsLeft; i++) {
            finalValStocks = (finalValStocks + newStocks) * interestStocks;
        }

        TextView valueStocks = (TextView) findViewById(R.id.finalStocks);
        valueStocks.setText("Your combined Stocks are valued at: $" + dollarCent.format(finalValStocks));

        return finalValStocks;
    }

    double displayOtherInfo(){
        DataStorage dataHolder = DataStorage.getInstance();

        DecimalFormat dollarCent = new DecimalFormat( "###,###,###,###.00");

        double finalValOther = (double) dataHolder.getOtherData("balanceOther");
        double interestOther = (1.0 + dataHolder.getOtherData("interestOther")/100.0);
        double newOther = (double) dataHolder.getOtherData("yearlyAddOther");
        for(int i = 0; i < MainActivity.yearsLeft; i++) {
            finalValOther = (finalValOther + newOther) * interestOther;
        }

        TextView valueOther = (TextView) findViewById(R.id.finalOther);
        valueOther.setText("Your combined Additional Investments are valued at: $" + dollarCent.format(finalValOther));

        return finalValOther;


    }

    public void displayTotalValue(double finalVal401k, double finalValOther, double finalValStocks){
        DecimalFormat dollarCent = new DecimalFormat( "###,###,###,###.00");

        // Display the value of all three combined
        double finalValue = finalVal401k + finalValOther + finalValStocks;
        TextView totalValue = (TextView) findViewById(R.id.finalVal);
        totalValue.setText("Your combined total retirement fund is valued at: $" + dollarCent.format(finalValue));

    }

}
