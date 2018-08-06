package com.example.dell.kuang.DCZ_okhttp;


import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by DELL on 2017/7/24.
 */

public class AddUpdate implements Interceptor{
    private Gson mGson = new Gson();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Response originalResponse = chain.proceed(originalRequest);
        String s = originalResponse.body().string();
        /*LoginBean result = mGson.fromJson(s, LoginBean.class);
        if(result.getCode()!=null){
            if(result.getCode().equals("20003")){
                Log.i("dcz","安全中心不可用");
                Request loginRequest = getLoginRequest();
                Response loginResponse = chain.proceed(loginRequest);
                String loginString = loginResponse.body().string();
                HttpBean resultLogin = mGson.fromJson(loginString, HttpBean.class);
                if(resultLogin.getCode().equals("10500")){
                    Log.i("dczz","安全中心不可用");
                }else if(resultLogin.getCode().equals("20000")) {
                    return chain.proceed(originalRequest);
                }else {
                    //下线通知

                }
            }
        }*/

        originalResponse = originalResponse.newBuilder()
                .body(ResponseBody.create(null, s))
                .build();
        return originalResponse;
    }

/*    private Request getLoginRequest() {
        return new Request.Builder()//http://110.79.11.5/user-safe-api/loginByRefreshToken
                .url("http://110.79.11.5/user-safe-api/loginByRefreshToken")
                .post(new FormBody.Builder()
                        .add("username", MyApplication.username)
                        .add("refreshToken",MyApplication.token)
                        .add("deviceUUID",MyApplication.device)
                        .build())
                .build();
    }*/

    public static class MyThrow extends IOException {

    }
}
