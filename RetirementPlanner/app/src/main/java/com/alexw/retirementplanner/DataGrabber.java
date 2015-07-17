package com.alexw.retirementplanner;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AlexW on 1/24/15.
 */
public class DataGrabber {

    private static final String TAG = "DataGrabber";
    public static final String SERVER_URL = "https://developer.yahoo.com/yql/conso" +
            "le/?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol" +
            "%20%3D%20%22%5EGSPC%22%20and%20startDate%20%3D%20%222014-01-01%22%20" +
            "and%20endDate%20%3D%20%222014-12-3" +
            "0%22%20&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    private List<DataObject> stockQuotes;
    DataObject firstQuote, lastQuote;


    public void run(){
        PostFetcher newFetch = new PostFetcher();
        newFetch.execute();
    }

    private void handlePostsList(List<DataObject> stockQuotes) {
        this.stockQuotes = stockQuotes;
        lastQuote.close = "15";
        for(DataObject stock : DataGrabber.this.stockQuotes){
            if (stock.date.equals("2014-01-01")){
                firstQuote = stock;
            }
            else if (stock.date.equals("2014-01-01")){
                lastQuote = stock;
            }
        }
    }

    private void failedLoadingPosts() {
    }

    private class PostFetcher extends AsyncTask<Void, Void, String>{


        @Override
        protected String doInBackground(Void... params){

            try {
                //Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpGet post = new HttpGet(SERVER_URL);

                //Perform the request and check the status code
                HttpResponse response = client.execute(post);
                StatusLine statusLine = response.getStatusLine();

                if(statusLine.getStatusCode() != HttpStatus.SC_OK){
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        //Read the server response and attempt to parse it as JSON
                        Reader reader = new InputStreamReader(content);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("yyyy-MM-dd");
                        Gson gson = gsonBuilder.create();
                        List<DataObject> quotes = Arrays.asList(gson.fromJson(reader, DataObject[].class));

                        content.close();

                        handlePostsList(quotes);
                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);
                        failedLoadingPosts();
                    }
                } else {
                    Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
                    failedLoadingPosts();
                }
            } catch(Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
                failedLoadingPosts();
            }
            return null;
        }
    }
}