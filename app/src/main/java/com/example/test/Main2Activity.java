package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("URL",url);

        Drawable drawable= null;
        try {
            drawable = new RequestTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageView imageView=findViewById(R.id.imgid);
        //Log.d("ffffffffffff",drawable.toString());
        imageView.setImageDrawable(drawable);


    }



    public class RequestTask extends AsyncTask<String, String, Drawable> {



        @Override
        protected Drawable doInBackground(String... strings) {
            Drawable drawable = LoadImageFromWebOperations(strings[0]);
            return drawable;
        }


        private Drawable LoadImageFromWebOperations(String url)
        {
            try{
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");
                return d;
            }catch (Exception e) {
                System.out.println("Exc="+e);
                return null;
            }
        }
    }
}
