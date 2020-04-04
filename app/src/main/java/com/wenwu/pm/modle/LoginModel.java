package com.wenwu.pm.modle;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.LoginReturnJson;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * @author:wenwudeng
 * @date:3:34 PM 3/23/2020
 *负责处理登录数据以及业务逻辑
 */
public class LoginModel implements IModel {



    public void login(String account, String password, Listener listener) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", account);
        map.put("password", password);
        OkHttpUtil.sendPostRequest("user/login", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
              //  LRReturnJson LRReturnJson = new Gson().fromJson(responseData, LRReturnJson.class);
                LoginReturnJson loginReturnJson = new Gson().fromJson(responseData, LoginReturnJson.class);
                Log.e("json", loginReturnJson+"");
                JsonUtil.loginJson =loginReturnJson;
                if (loginReturnJson.getCode().equals("3000")) {
                    listener.onSuccess(loginReturnJson);
                }else {
                    listener.onFail(loginReturnJson);
                }
            }
        });
    }


}
