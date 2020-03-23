package com.wenwu.pm.modle;

import com.google.gson.Gson;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:wenwudeng
 * @date:11:32 PM 3/23/2020
 */
public class RegisterModel implements IModel{

    public void register(String account, String password, String verifyCode, Listener listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", account);
        params.put("password", password);
        params.put("verifyCode", verifyCode);

        OkHttpUtil.sendPostRequest("register", params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String  responseData = response.body().string();
                LRReturnJson json = new Gson().fromJson(responseData, LRReturnJson.class);
                if (json.getCode().equals("3003")) {
                    listener.onSuccess(json);
                }else {
                    listener.onFail(json);
                }
            }
        });

    }
}
