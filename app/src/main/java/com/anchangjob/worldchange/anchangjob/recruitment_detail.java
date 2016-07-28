package com.anchangjob.worldchange.anchangjob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class recruitment_detail extends Activity {

    Data mydata=(Data) getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recruitment_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int recruitment_id=bundle.getInt("recruitment");

        JSONObject recruitment=getdata(recruitment_id);

        TextView textview_title=(TextView)findViewById(R.id.textView40);
        TextView textview_content=(TextView)findViewById(R.id.textView41);
        TextView textview_type=(TextView)findViewById(R.id.textView47);
        TextView textview_period=(TextView)findViewById(R.id.textView49);
        TextView textview_salary_down=(TextView)findViewById(R.id.textView51);
        TextView textview_salary_top=(TextView)findViewById(R.id.textView53);
        TextView textview_age_down=(TextView)findViewById(R.id.textView55);
        TextView textview_age_top=(TextView)findViewById(R.id.textView57);
        TextView textview_experience=(TextView)findViewById(R.id.textView59);
        TextView textview_number=(TextView)findViewById(R.id.textView61);
        TextView textview_name=(TextView)findViewById(R.id.textView43);
        TextView textview_phone=(TextView)findViewById(R.id.textView45);

        Button bt_favor = (Button)findViewById(R.id.button15);
        bt_favor.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                mydata=(Data)getApplication();
                favor(recruitment_id,mydata.userid);
            }
        });

        try {
            textview_title.setText(recruitment.getString("title"));
            textview_content.setText(recruitment.getString("content"));
            textview_type.setText(recruitment.getString("job_type"));
            textview_period.setText(recruitment.getString("work_period"));
            textview_salary_down.setText(recruitment.getString("salary_down"));
            textview_salary_top.setText(recruitment.getString("salary_top"));
            textview_age_down.setText(recruitment.getString("age_down"));
            textview_age_top.setText(recruitment.getString("age_top"));
            textview_experience.setText(recruitment.getString("experience"));
            textview_number.setText(recruitment.getString("amount"));
            textview_name.setText(recruitment.getString("responsible_name"));
            textview_phone.setText(recruitment.getString("responsible_phone"));



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    JSONObject getdata(int id){
        mydata=(Data)getApplication();
        JSONObject jsonresult=new JSONObject();
        JSONObject recruitment=new JSONObject();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"home/get_info/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("recruitment_id", Integer.toString(id)));
        try {
            entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        // 3.客户端带着请求对象请求服务器端
        try {
            // 服务器端返回请求的数据
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 解析请求返回的数据
            if (httpResponse != null
                    && httpResponse.getStatusLine().getStatusCode() == 200) {
                String element = EntityUtils.toString(httpResponse.getEntity(),
                        HTTP.UTF_8);
                if (element.startsWith("{")) {
                    try {
                        jsonresult= new JSONObject(element);
                        recruitment=jsonresult.getJSONObject("response");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recruitment;
    }

    boolean favor(int recruitment_id,int user_id){
        HttpPost httpPost = new HttpPost(mydata.MYURL+"home/collect/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("collect_ID", Integer.toString(recruitment_id)));
        list.add(new BasicNameValuePair("uid", Integer.toString(user_id)));
        try {
            entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        // 3.客户端带着请求对象请求服务器端
        try {
            // 服务器端返回请求的数据
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 解析请求返回的数据
            if (httpResponse != null
                    && httpResponse.getStatusLine().getStatusCode() == 200) {
                Toast.makeText(recruitment_detail.this, "收藏成功！", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(recruitment_detail.this, "收藏失败，请检查网络是否正常！", Toast.LENGTH_SHORT).show();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
