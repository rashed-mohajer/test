package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv=(ListView) findViewById(R.id.listvId);



            String file=getfile(this);

        try {
            List<String> list = new ArrayList<String>();
            JSONArray json=new JSONArray(file);
            for(int i=0;i<json.length();i++){

                JSONObject info=json.getJSONObject(i);
                Log.d("DDDDDDDDDDD",String.valueOf(info.get("name")));
                list.add(String.valueOf(info.get("name")));
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list );
            lv.setAdapter(arrayAdapter);
            Log.i("json file", String.valueOf(json.get(1)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                handleOnclick(String.valueOf(position));
            }
        });

    }

    private String getfile(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("haraj.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void handleOnclick(String index) {
        JSONArray json=null;
        String file=getfile(this);
        List<String> list = new ArrayList<String>();
        try {
             json=new JSONArray(file);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject info= (JSONObject) json.get(Integer.valueOf(index));
            String url= info.getString("url");
            Intent intent=new Intent(this,Main2Activity.class);
            intent.putExtra("url",url);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
