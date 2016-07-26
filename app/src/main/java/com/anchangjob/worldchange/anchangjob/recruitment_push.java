package com.anchangjob.worldchange.anchangjob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class recruitment_push extends Activity {

    private EditText title;
    private EditText content;
    private Spinner job_type;
    private Spinner job_period;
    private Spinner age_down;
    private Spinner age_top;
    private Spinner salary_down;
    private Spinner salary_top;
    private Spinner experience;
    private Spinner amount;

    private String s_title;
    private String s_content;
    private String s_job_type;
    private String s_job_period;
    private String s_age_down;
    private String s_age_top;
    private String s_salary_down;
    private String s_salary_top;
    private String s_experience;
    private String s_amount;

    private Button push_submit;
    Data mydata=(Data)getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_push);

        title=(EditText)findViewById(R.id.editText6);
        content=(EditText)findViewById(R.id.editText7);
        job_type=(Spinner)findViewById(R.id.spinner2);
        job_period=(Spinner)findViewById(R.id.spinner3);
        age_down=(Spinner)findViewById(R.id.spinner4);
        age_top=(Spinner)findViewById(R.id.spinner5);
        salary_down=(Spinner)findViewById(R.id.spinner6);
        salary_top=(Spinner)findViewById(R.id.spinner7);
        experience=(Spinner)findViewById(R.id.spinner8);
        amount=(Spinner)findViewById(R.id.spinner9);
        push_submit=(Button)findViewById(R.id.button11);

        push_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_title=title.getText().toString();
                s_content=content.getText().toString();
                s_job_type=job_type.getSelectedItem().toString();
                s_age_down=age_down.getSelectedItem().toString();
                s_age_top=age_top.getSelectedItem().toString();
                s_salary_down=salary_down.getSelectedItem().toString();
                s_salary_top=salary_top.getSelectedItem().toString();
                s_experience=experience.getSelectedItem().toString();
//                s_amount=amount.getSelectedItem().toString();

                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("title", s_title));
                list.add(new BasicNameValuePair("content", s_content));
                list.add(new BasicNameValuePair("age_down", s_age_down));
                list.add(new BasicNameValuePair("age_top", s_age_top));
                list.add(new BasicNameValuePair("salary_down", s_salary_down));
                list.add(new BasicNameValuePair("salary_top", s_salary_top));
                list.add(new BasicNameValuePair("experience", s_experience));
                list.add(new BasicNameValuePair("amount", "debug"));
                push(list);
            }
        });

    }
    private void push(List<NameValuePair> list){
        mydata=(Data)getApplication();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"user/recruitment_push/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        HttpClient httpClient = new DefaultHttpClient();
        try {
            // 服务器端返回请求的数据
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 解析请求返回的数据
            if (httpResponse != null
                    && httpResponse.getStatusLine().getStatusCode() == 200) {
                Toast.makeText(recruitment_push.this, "招聘信息发布成功！", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(recruitment_push.this,my_mainactivity.class);
                startActivity(intent2);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
