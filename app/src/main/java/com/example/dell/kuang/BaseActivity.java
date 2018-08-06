package com.example.dell.kuang;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dell.kuang.DCZ_okhttp.StatusBarUtil;
import com.example.dell.kuang.DCZ_util.ActivityUtils;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        StatusBarUtil.setImgTransparent(this);      //这行是让标题沉浸
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏
        ActivityUtils.getInstance().pushActivity(this);
        Log.i("BaseActivity",getClass().getSimpleName());
        Log.i("dcz栈的数量", ActivityUtils.getInstance().ActivitySize()+"");
        for(int i=0;i<ActivityUtils.getInstance().ActivitySize();i++){
            Log.i("dcz_栈",ActivityUtils.getInstance().getActivity(i)+"");
        }
    }
}
