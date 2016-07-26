package com.anchangjob.worldchange.anchangjob.mine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.anchangjob.worldchange.anchangjob.R;

public class mine_myfavorite extends Activity {

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
