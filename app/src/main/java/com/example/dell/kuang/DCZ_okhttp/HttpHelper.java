package com.example.dell.kuang.DCZ_okhttp;

import android.net.ParseException;

import com.example.dell.kuang.DCZ_application.MyApplication;
import com.example.dell.kuang.R;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 *
 * @author yiyang
 */
public class HttpHelper {
    public static  MultipartBody.Part getPicPart(String paramName, String path){
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(paramName, file.getName(), requestBody);
        return part;
    }

    /**
     * 使用part注解时在Post请求中默认的Content-Type类型是“application/json”，这就说明我们在接口中不能再使用@Part注解了，我们需要在代码中指定类型。
     * 这时使用@PartMap并
     * 将文字参数类型转换为“text/plain”
     * @param value
     * @return
     */
    public static RequestBody toTextPlain(String value){
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }


    /**
     * 动态设置超时时间
     */
    public static void setDynamicTimeout(Retrofit retrofit, int timeout) {
        try {
            //1、private final okhttp3.Call.Factory callFactory;   Retrofit 的源码 构造方法中
            Field callFactoryField = retrofit.getClass().getDeclaredField("callFactory");
            callFactoryField.setAccessible(true);
            //2、callFactory = new OkHttpClient();   Retrofit 的源码 build()中
            OkHttpClient client = (OkHttpClient) callFactoryField.get(retrofit);
            //3、OkHttpClient(Builder builder)     OkHttpClient 的源码 构造方法中
            Field connectTimeoutField = client.getClass().getDeclaredField("connectTimeout");
            connectTimeoutField.setAccessible(true);
            connectTimeoutField.setInt(client, timeout);
            Field readTimeoutField = client.getClass().getDeclaredField("readTimeout");
            readTimeoutField.setAccessible(true);
            readTimeoutField.setInt(client, timeout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static String handleException(Throwable t){
        int errorMsg = R.string.default_net_failed;
        /*if(t instanceof ApiException){
            return t.getMessage();
        }if (t instanceof HttpException) {
            errorMsg = R.string.http_net_failed;
        } else */if (t instanceof ConnectException) {
            errorMsg = R.string.connect_net_failed;
        } else if (t instanceof SocketException
                || t instanceof SocketTimeoutException
                || t instanceof TimeoutException) {  //VPN open
            errorMsg = R.string.socket_timeout_net_failed;
        } else if (t instanceof UnknownHostException) {
            errorMsg = R.string.unknownhost__net_failed;
        } else if (t instanceof UnknownServiceException) {
            errorMsg = R.string.unknownservice_net_failed;
        } else if (t instanceof IOException) {  //飞行模式等
            errorMsg = R.string.io_net_failed;
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof JsonSyntaxException
                || t instanceof ParseException) {   //  解析错误
            errorMsg = R.string.parse_net_failed;
        } else if (t instanceof RuntimeException) {
            errorMsg = R.string.runtime_net_failed;
        }
        return MyApplication.getContext().getString(errorMsg);
    }

    public static Map<String, RequestBody> getMomentPicMap(List<File> photos) {
        HashMap<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < photos.size(); i++) {
            File file = photos.get(i);
            map.put("p"+(i+1)+"\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
        }
        return map;
    }
}
