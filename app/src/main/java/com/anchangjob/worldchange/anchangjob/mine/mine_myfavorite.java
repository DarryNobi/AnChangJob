package com.anchangjob.worldchange.anchangjob.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.anchangjob.worldchange.anchangjob.R;

public class mine_myfavorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_mine_myfavorite);
        LinearLayout item=(LinearLayout)findViewById(R.id.my_favorite_item);
        LinearLayout box=(LinearLayout)findViewById(R.id.my_favorite_box);

        for (int i=0;i<5;i++)
            box.addView(new LinearLayout(item.getContext()),i);//////////////////////??????????????????

    }

}
