package com.anchangjob.worldchange.anchangjob;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class my_register extends Activity {

    private EditText register_username;
    private EditText register_passwd;
    private EditText reregister_passwd;
    private Button register_submit;
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

                if(!checkEdit()){
                    return;
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