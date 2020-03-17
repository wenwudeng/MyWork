package com.wenwu.pm.utils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author:wenwudeng
 * @date:11:20 AM 3/17/2020
 */
public class OkHttpUtil {
    private static  OkHttpClient client = new OkHttpClient();
    public static final String USER_PATH = "http://10.0.2.2:8080/api/user/";

    //登录
    public static void loginWithOkHttp(String address,String account,String password,okhttp3.Callback callback){

        RequestBody body = new FormBody.Builder()
                .add("phone",account)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(USER_PATH+address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //注册
   public static void registerWithOkHttp(String address,String code,String account,String password,okhttp3.Callback callback){
        RequestBody body = new FormBody.Builder()
                .add("phone",account)
                .add("password",password)
                .add("verifyCode",code)
                .build();
        Request request = new Request.Builder()
                .url(USER_PATH+address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }



}
