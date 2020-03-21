package com.wenwu.pm.utils;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * @author:wenwudeng
 * @date:11:20 AM 3/17/2020
 */
public class OkHttpUtil {
    private static  OkHttpClient client = new OkHttpClient();
    public static final String USER_PATH = "http://192.168.1.112:8080/api/user/";

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
   public static void registerWithOkHttp(String address, String code, String account, String password, Callback callback){
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

    public static void showInfoWithOkHttp(String address, String phone, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("phone",phone)
                .build();
        Request request = new Request.Builder()
                .url(USER_PATH+address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void updateInfo(String address, String id,String photo, String userName,String gender,String city,String profile,String pet,Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("userId",id)
                .add("photo",photo)
                .add("userName",userName)
                .add("gender",gender)
                .add("city",city)
                .add("profile",profile)
                .add("pet",pet)
                .build();
        Request request = new Request.Builder()
                .url(USER_PATH+address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }





}
