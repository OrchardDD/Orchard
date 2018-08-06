package com.example.dell.kuang.DCZ_application;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.chiclam.android.updater.Updater;
import com.chiclam.android.updater.UpdaterConfig;
import com.example.dell.kuang.R;

import org.litepal.LitePal;

/**
 * Created by DELL on 2017/10/19.
 */

public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        LitePal.initialize(this);//litepal数据库
    }

    public static Context getContext(){
        return context;
    }

    private void down(){
        /*UpdaterConfig config = new UpdaterConfig.Builder(INSTANCE)
                .setTitle(getResources().getString(R.string.app_name))
                .setDescription(getString(R.string.system_download_description))
                .setFileUrl(path)
                .setCanMediaScanner(true)
                .build();
        Updater.get().showLog(true).download(config);
        Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        INSTANCE.startActivity(viewDownloadIntent);*/
    }
    private void sf(){
        SharedPreferences sf = getSharedPreferences("user",MODE_PRIVATE);
        String username = sf.getString("username","");//第二个参数为默认值
        sf.edit().putString("username","张三").commit();
    }
}
