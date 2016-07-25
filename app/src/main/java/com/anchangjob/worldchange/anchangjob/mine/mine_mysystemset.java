package com.anchangjob.worldchange.anchangjob.mine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anchangjob.worldchange.anchangjob.Data;
import com.anchangjob.worldchange.anchangjob.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class mine_mysystemset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_mysystemset);

        Button bt_update = (Button) findViewById(R.id.button14);
        bt_update.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                if(cheakupdate())
                {

                    Toast.makeText(mine_mysystemset.this, "正在下载最新版软件，请稍等！", Toast.LENGTH_SHORT).show();
                    File file=download("http://anchangjob.applinzi.com/download.php");
                    if(file!=null)
                        openFile(file);
                }
                else{

                    Toast.makeText(mine_mysystemset.this, "当前已经是最新版本了！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean cheakupdate(){

        return true;
    }
    File download(String httpUrl){
        final String fileName = "anchangjob.apk";
        File tmpFile = new File("/sdcard/anchangjob");
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }
        final File file = new File("/sdcard/anchangjob/" + fileName);

        try {
            URL url = new URL(httpUrl);
            try {
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[256];
                conn.connect();
                double count = 0;
                if (conn.getResponseCode() >= 400) {
                    Toast.makeText(mine_mysystemset.this, "连接超时", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    while (count <= 100) {
                        if (is != null) {
                            int numRead = is.read(buf);
                            if (numRead <= 0) {
                                break;
                            } else {
                                fos.write(buf, 0, numRead);
                            }

                        } else {
                            break;
                        }

                    }
                }

                fos.close();
                is.close();
                conn.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return file;
    }

    private void openFile(File file) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
