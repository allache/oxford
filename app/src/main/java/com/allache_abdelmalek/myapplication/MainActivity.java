package com.allache_abdelmalek.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import static com.allache_abdelmalek.myapplication.R.id.activity_main;
public class MainActivity extends AppCompatActivity {


        String url;
        String text;
        String spinner1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        }

        public void requestButtonClick (View v){

            TextView txtView = (TextView) findViewById(R.id.txt);
            Spinner spinner=(Spinner) findViewById(R.id.spinner);
            EditText tx =(EditText) findViewById(R.id.editText);


            text = tx.getText().toString();
            url = dictionaryEntries();
            spinner1 =spinner.getSelectedItem().toString();

            myDictionaireRequest obj_myDictionaireRequest = new myDictionaireRequest(this);
            obj_myDictionaireRequest.execute(url);
        }


        public String dictionaryEntries() {

            String language = spinner1;
            String word = text;
            String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
            return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
        }
    }
