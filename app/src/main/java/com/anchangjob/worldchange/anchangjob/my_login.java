package com.anchangjob.worldchange.anchangjob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import org.apache.http.*;

public class my_login extends Activity implements OnClickListener {
    private EditText login_username;
    private EditText login_password;
    private Button user_login_button;
    private Button user_register_button;
    JSONObject jsonObject=new JSONObject();
 Data mydata=(Data)getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_my_login);
        initWidget();



    }
    private void initWidget()
    {
        login_username=(EditText)findViewById(R.id.login_username);
        login_password=(EditText)findViewById(R.id.login_password);
        user_login_button=(Button)findViewById(R.id.user_login_button);
        user_register_button=(Button)findViewById(R.id.user_register_button);
        user_login_button.setOnClickListener(this);
        user_register_button.setOnClickListener(this);
        login_username.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    String username=login_username.getText().toString().trim();
                    if(username.length()<4){
                        Toast.makeText(my_login.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT);
                    }
                }
            }

        });
        login_password.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    String password=login_password.getText().toString().trim();
                    if(password.length()<4){
                        Toast.makeText(my_login.this, "密码不能小于4个字符", Toast.LENGTH_SHORT);
                    }
                }
            }

        });
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.user_login_button:
                if(checkEdit())
                {
                    login();
                }
                int code=0;
                try {
                     code=jsonObject.getInt("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(code==1)
                {mydata=(Data)getApplication();
                if(mydata!=null)
                {mydata.setislogedin(true);}
                    Toast.makeText(my_login.this, "登录成功！", Toast.LENGTH_SHORT).show();
                Intent intent3=new Intent(my_login.this,my_mainactivity.class);
                startActivity(intent3);}
                else
                    Toast.makeText(my_login.this, "登录失败！请检查用户名和密码是否正确！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_register_button:
                Intent intent2=new Intent(my_login.this,my_register.class);
                startActivity(intent2);
                break;
        }
    }

    private boolean checkEdit(){
        if(login_username.getText().toString().trim().equals("")){
            Toast.makeText(my_login.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(login_password.getText().toString().trim().equals("")){
            Toast.makeText(my_login.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }

    private void login(){
        HttpURLConnection urlConnection = null;
        String str=null;
        String username=login_username.getText().toString();
        String password=login_password.getText().toString();
        /*try {
            mydata=(Data) getApplication();
            URL url = new URL(mydata.MYURL+"user/login/"+"account/"+username+"/"+"password/"+password);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.connect();
            InputStream in =urlConnection.getInputStream();
            if(in!=null) {
                byte[] data = new byte[1024];
                int len;
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                while ((len = in.read(data)) != -1) {
                    outStream.write(data, 0, len);
                }
                in.close();
                String json = new String(data); // 把字符串组转换成字符串
                str = json;

                try {
                    jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            }
            //int total = jsonObject.getInt("count");
            //String keywords = jsonObject.getString("keywords");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // 1.创建请求对象
        mydata=(Data)getApplication();
        HttpPost httpPost = new HttpPost(mydata.MYURL+"user/login/");
        // post请求方式数据放在实体类中
        HttpEntity entity = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("account", username));
        list.add(new BasicNameValuePair("password", password));
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
                        jsonObject= new JSONObject(element);
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
    }
}