package com.anchangjob.worldchange.anchangjob;

import android.app.Application;

public class Data extends Application {
    private int tab;
    public int userid;
    public String username;
    public boolean islogedin;

    public int gettab(){
        return this.tab;
    }
    public void settab(int n){
        this.tab= n;
    }
    @Override
    public void onCreate(){
        tab = 0;
        super.onCreate();
    }
}