package com.anchangjob.worldchange.anchangjob;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

public class Data extends Application implements Serializable {
    private int tab=-1;
    public int userid=-1;
    public String username="0";
    public boolean islogedin=false;
    public int user_type=-1;
    public String MYURL="http://anchangjob.applinzi.com/";
    public int gettab(){
        return this.tab;
    }
    public void settab(int n){
        this.tab= n;
    }
    public void setislogedin(boolean bl){
        this.islogedin= bl;
    }
    @Override
    public void onCreate(){
        tab = 0;
        super.onCreate();
    }

public void update(Data d){
    this.userid=d.userid;
    this.username=d.username;
    this.islogedin=d.islogedin;
    this.user_type=d.user_type;
    this.MYURL=d.MYURL;

}
    public  void fileSave(Activity activity, Data mydata) {

        //保存在本地


        //保存在sd卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            File sdFile = new File(sdCardDir+"/anchangjob/", "mydata.out");


            try {
                FileOutputStream fos = new FileOutputStream(sdFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(mydata);// 写入
                fos.close(); // 关闭输出流
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //Toast.makeText(WebviewTencentActivity.this, "成功保存到sd卡", Toast.LENGTH_LONG).show();

        }
        else {
            try {
                // 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
                FileOutputStream fos = activity.openFileOutput("mydata.out", Context.MODE_WORLD_READABLE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(mydata);// 写入
                fos.close(); // 关闭输出流
                //Toast.makeText(WebviewTencentActivity.this, "保存oAuth_1成功",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText(WebviewTencentActivity.this, "出现异常1",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(WebviewTencentActivity.this, "出现异常2",Toast.LENGTH_LONG).show();
            }
        }
    }

    public  Data fileread(Activity activity) {
        Data mydata = null;

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            File sdFile = new File(sdCardDir, "mydata.out");

            try {
                FileInputStream fis = new FileInputStream(sdFile);   //获得输入流
                ObjectInputStream ois = new ObjectInputStream(fis);
                mydata = (Data) ois.readObject();
                ois.close();
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (OptionalDataException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            try {
                FileInputStream fis = activity.openFileInput("mydata.out");   //获得输入流
                ObjectInputStream ois = new ObjectInputStream(fis);
                mydata = (Data) ois.readObject();

                //tv.setText(per.toString());                           //设置文本控件显示内容
                // Toast.makeText(ShareTencentActivity.this,"读取成功",Toast.LENGTH_LONG).show();//弹出Toast消息
            } catch (StreamCorruptedException e) {
                //Toast.makeText(ShareTencentActivity.this,"出现异常3",Toast.LENGTH_LONG).show();//弹出Toast消息
                e.printStackTrace();
            } catch (OptionalDataException e) {
                //Toast.makeText(ShareTencentActivity.this,"出现异常4",Toast.LENGTH_LONG).show();//弹出Toast消息
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                //Toast.makeText(ShareTencentActivity.this,"出现异常5",Toast.LENGTH_LONG).show();//弹出Toast消息
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                //Toast.makeText(ShareTencentActivity.this,"出现异常6",Toast.LENGTH_LONG).show();//弹出Toast消息
                e.printStackTrace();
            }

        }
        return mydata;
    }

}