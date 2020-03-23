package com.wenwu.pm.utils;


import java.util.Map;
import java.util.Set;

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



     /*// 发送GET请求
    public static void sendGetRequest(String url, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient(); // 创建一个client
        Request request = new Request.Builder().url(url).build(); // 组装一个Request对象
        client.newCall(request).enqueue(callback); // 发送请求
    }*/

    // 发送POST请求
    public static void sendPostRequest(String url, Map<String, Object> map, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient(); // 创建一个client
        // POST请求的参数需要放在一个RequestBody对象中，它由FormBody.Builder建造者类来建造（建造者模式）
        FormBody.Builder builder = new FormBody.Builder();
        if(map != null){
            // 不能直接把map转为RequestBody，必须遍历map的key，并逐一地往builder中添加对应的value
            Set<String> keys = map.keySet();
            for(String key : keys){ // 注意：RequestBody的参数只能是字符串类型的
                String value = map.get(key).toString();
                builder.add(key, value);
                System.out.println(key+":"+value);
            }
        }
        RequestBody requestBody = builder.build(); // 最后利用builder来生成一个RequestBody实例
        // 组装一个Request对象（这次有额外传入RequestBody）
        Request request = new Request.Builder().url(USER_PATH+url).post(requestBody).build();
        client.newCall(request).enqueue(callback); // 发送请求
    }



}
