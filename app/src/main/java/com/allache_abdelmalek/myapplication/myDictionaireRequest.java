package com.allache_abdelmalek.myapplication;
import com.allache_abdelmalek.myapplication.MainActivity;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Abdelmalek on 19/12/2018.
 */



public class myDictionaireRequest extends AsyncTask<String,Integer,String> {

    final String app_id = "428e61e1";
    final String app_key = "e47508582807eb66ad0cfc6f06ab3bd1";
    String myURL;
    Context context;
    String def="";

    public myDictionaireRequest(Context context){

        this.context=context;
    }

    @Override
    protected String doInBackground(String... params) {
        myURL=params[0];
        try {
            URL url = new URL(myURL);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e) {

            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);

        TextView txtView = (TextView) ((MainActivity)context).findViewById(R.id.txt);

        try {
            JSONObject js =new JSONObject(s);
            JSONArray result = js.getJSONArray("results");

            JSONObject lentries = result.getJSONObject(0);
            JSONArray laArray =lentries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray e =entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

             JSONObject d = sensesArray.getJSONObject(0);
            JSONArray de =d.getJSONArray("definitions");

         /* JSONObject d = sensesArray.getJSONObject(0);
            JSONArray de =d.getJSONArray("short_definitions");*/

            def = de.getString(0);
            txtView.setText(def);

        }catch (JSONException e) {


        }


    }

}
