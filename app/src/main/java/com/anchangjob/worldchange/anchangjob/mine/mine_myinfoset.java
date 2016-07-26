package com.anchangjob.worldchange.anchangjob.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.util.Pools;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anchangjob.worldchange.anchangjob.Data;
import com.anchangjob.worldchange.anchangjob.R;
import com.anchangjob.worldchange.anchangjob.my_register;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class mine_myinfoset extends Activity {


    private EditText username;
    private EditText phone;
    private Spinner sex;
    private Spinner year;
    private Spinner month;
    private Spinner education;
    private Spinner jiguan;
    private EditText self_introduction;
    private Button submit;
    int statu=0;
Data mydata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_myinfoset);

        username=(EditText)findViewById(R.id.editText8);
        phone=(EditText)findViewById(R.id.editText9);
        sex=(Spinner)findViewById(R.id.spinner);
        year=(Spinner)findViewById(R.id.spinner10);
        month=(Spinner)findViewById(R.id.spinner11);
        education=(Spinner)findViewById(R.id.spinner12);
        jiguan=(Spinner)findViewById(R.id.spinner13);
        self_introduction=(EditText)findViewById(R.id.editText10);
        submit=(Button)findViewById(R.id.button12);

        username.setEnabled(false);
        phone.setEnabled(false);
        sex.setEnabled(false);
        year.setEnabled(false);
        month.setEnabled(false);
        education.setEnabled(false);
        jiguan.setEnabled(false);
        self_introduction.setEnabled(false);

        String s_username;
        String s_phone;
        String s_sex;
        String s_year;
        String s_month;
        String s_education;
        String s_jiguan;
        String s_self_introduction;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statu==0){// 修改信息按钮
                    submit.setText("确认修改");
                    username.setEnabled(true);
                    phone.setEnabled(true);
                    sex.setEnabled(true);
                    year.setEnabled(true);
                    month.setEnabled(true);
                    education.setEnabled(true);
                    jiguan.setEnabled(true);
                    self_introduction.setEnabled(true);
                    statu=1;
                }
                else if (statu==1){
                    statu=0;
                    pushinfo();
                }
            }
        });

        mydata=(Data)getApplication();
        JSONObject userinfo=getinfo(mydata.userid);
        int code= 0;
        JSONArray j=new JSONArray();
        try {
            code = userinfo.getInt("code");
            j=userinfo.getJSONArray("response");

            if(code==1) {
                s_username=((JSONObject)(j.get(0))).getString("username");
                s_phone=((JSONObject)(j.get(0))).getString("phone");
                s_sex=((JSONObject)(j.get(0))).getString("sex");
                s_year=((JSONObject)(j.get(0))).getString("age_year");
                s_month=((JSONObject)(j.get(0))).getString("age_month");
                s_education=((JSONObject)(j.get(0))).getString("education");
                s_jiguan=((JSONObject)(j.get(0))).getString("origin");
                s_self_introduction=((JSONObject)(j.get(0))).getString("introduction");
                username.setText(s_username);
                phone.setText(s_phone);
                self_introduction.setText(s_self_introduction);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    JSONObject getinfo(int id){
        JSONObject jo=new JSONObject();

        mydata=(Data)getApplication();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"user/show_user/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("id", Integer.toString(id)));
        try {
            entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 2.创建客户端对象
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
                        jo= new JSONObject(element);
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

        return  jo;
    }
    public void pushinfo(){


        mydata=(Data)getApplication();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"user/modify_info/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("id", Integer.toString(mydata.userid)));
        list.add(new BasicNameValuePair("username",username.getText().toString()));
        list.add(new BasicNameValuePair("phone",phone.getText().toString()));
        list.add(new BasicNameValuePair("sex",sex.getSelectedItem().toString()));
        list.add(new BasicNameValuePair("age_year",year.getSelectedItem().toString()));
        list.add(new BasicNameValuePair("age_month",month.getSelectedItem().toString()));
        list.add(new BasicNameValuePair("education",education.getSelectedItem().toString()));
        list.add(new BasicNameValuePair("origin",jiguan.getSelectedItem().toString()));
        list.add(new BasicNameValuePair("introduction",self_introduction.getText().toString()));
        try {
            entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 2.创建客户端对象
        HttpClient httpClient = new DefaultHttpClient();
        // 3.客户端带着请求对象请求服务器端
        try {
            // 服务器端返回请求的数据
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 解析请求返回的数据
            if (httpResponse != null
                    && httpResponse.getStatusLine().getStatusCode() == 200) {

                Toast.makeText(mine_myinfoset.this, "修改成功！", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(mine_myinfoset.this,com.anchangjob.worldchange.anchangjob.my_mainactivity.class);
                startActivity(intent2);
            }
            else{

                Toast.makeText(mine_myinfoset.this, "修改失败！", Toast.LENGTH_SHORT).show();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
