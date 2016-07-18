package com.anchangjob.worldchange.anchangjob;

import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HttpURLConnection urlConnection = null;
        String str=null;
       // StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        try {
            URL url = new URL("http://202.108.22.5");
            urlConnection = (HttpURLConnection)url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.connect();
            InputStream in =urlConnection.getInputStream();

            byte[] data = new byte[1024];
            int len;
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            while ((len=in.read(data)) != -1) {
                outStream.write(data, 0, len);}
            in.close();
            String json = new String(data); // 把字符串组转换成字符串
            str=json;

            try {
                JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
            }catch(JSONException je){
                je.printStackTrace();
            }
            //int total = jsonObject.getInt("count");
            //String keywords = jsonObject.getString("keywords");

        } catch (IOException e) {
            e.printStackTrace();
        }


        TextView textview= (TextView) findViewById(R.id.textView7);
        textview.setText("("+str+")");

        //////////////////////////////////////////
        Button button_activity = (Button) this.findViewById(R.id.button);
        Button button_activity1 = (Button) this.findViewById(R.id.button2);

        button_activity.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        button_activity1.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
    }


}
