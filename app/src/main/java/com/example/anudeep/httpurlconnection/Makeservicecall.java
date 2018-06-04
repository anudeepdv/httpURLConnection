package com.example.anudeep.httpurlconnection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anudeep on 02/07/17.
 */

class Makeservicecall {

    public String makeServiceCall() {

        String response = "";
        String url = "http://api.androidhive.info/contacts/";

        try {
            URL urlnew = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) urlnew.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());

            response = convertinputstream(in);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response ;

    }

    private String convertinputstream(InputStream in) {

        String response = "";

        StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            while((response = reader.readLine()) != null) {

                sb.append(response).append("\n");


            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return  sb.toString();
    }
}
