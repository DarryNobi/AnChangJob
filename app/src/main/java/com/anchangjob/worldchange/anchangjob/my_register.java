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
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
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
public class my_register extends Activity {

    private EditText register_username;
    private EditText register_passwd;
    private EditText reregister_passwd;
    private Button register_submit;
    JSONObject jsonObject=new JSONObject();
    Data mydata=(Data)getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_my_register);
        register_username=(EditText)findViewById(R.id.register_username);
        register_passwd=(EditText)findViewById(R.id.register_passwd);
        reregister_passwd=(EditText)findViewById(R.id.reregister_passwd);
        register_submit=(Button)findViewById(R.id.register_submit);
        register_username.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_username.getText().toString().trim().length()<4){
                        Toast.makeText(my_register.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_passwd.getText().toString().trim().length()<6){
                        Toast.makeText(my_register.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        reregister_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
                        Toast.makeText(my_register.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register_submit.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                if (!checkEdit()) {
                    return;
                }
                String username = register_username.getText().toString();
                String password = register_passwd.getText().toString();
                HttpURLConnection urlConnection = null;
                String str = null;
                /*try {

                    mydata=(Data) getApplication();
                    URL url = new URL(mydata.MYURL+"?"+username+"&"+password);
                    urlConnection = (HttpURLConnection)url.openConnection();
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
                            JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
                        } catch (JSONException je) {
                            je.printStackTrace();
                        }
                    }
                    //int total = jsonObject.getInt("count");
                    //String keywords = jsonObject.getString("keywords");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
                mydata=(Data)getApplication();
                HttpPost httpPost = new HttpPost(mydata.MYURL+"user/register/");
                // post请求方式数据放在实体类中
                HttpEntity entity = null;
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("username", username));
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
                    int code=0;
                    try {
                        code=jsonObject.getInt("code");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(code==1)

                    {Toast.makeText(my_register.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(my_register.this,my_login.class);
                        startActivity(intent2);}
                    else
                        Toast.makeText(my_register.this, "注册失败！", Toast.LENGTH_SHORT).show();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkEdit(){
        if(register_username.getText().toString().trim().equals("")){
            Toast.makeText(my_register.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(register_passwd.getText().toString().trim().equals("")){
            Toast.makeText(my_register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){
            Toast.makeText(my_register.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }

}