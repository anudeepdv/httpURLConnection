package com.example.anudeep.httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_read = (Button) findViewById(R.id.btn_read);

        btn_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_read :

                readdata();



                break;

        }
    }

    private void readdata() {

        getcontacts cts = new getcontacts();
        cts.execute();

       /*ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show(); */

    }

    class getcontacts extends AsyncTask<Void,Void,Void>{

        String response;
        ProgressDialog dialog;
        String id;
        String ph;
        @Override
        protected Void doInBackground(Void... params) {

            Makeservicecall ch = new Makeservicecall() ;

             response = ch.makeServiceCall();

            try {
                JSONObject obj = new JSONObject(response);

               JSONArray arr = obj.getJSONArray("contacts");

                for(int i=0; i<arr.length() ; i++)
                {
                    JSONObject insidearr = arr.getJSONObject(i);

                     id = insidearr.getString("id");


                    JSONObject phone =  insidearr.getJSONObject("phone");

                     ph = phone.getString("mobile");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d("response", response);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),ph,Toast.LENGTH_LONG).show();


            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog= new ProgressDialog(MainActivity.this);
            dialog.setMessage("loading");
            dialog.show();


        }
    }

}
