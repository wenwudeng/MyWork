package com.wenwu.pm.modle;

import android.util.Log;

import com.google.android.material.animation.ImageMatrixProperty;
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
 * @date:2:20 PM 3/24/2020
 */
public class EditInfoModel implements IModel {

    public void save(int id, String photo, String userName, String gender, String city, String profile, String pet, Listener listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        params.put("photo", photo);
        params.put("userName", userName);
        params.put("gender", gender);
        params.put("city", city);
        params.put("profile", profile);
        params.put("pet", pet);
        OkHttpUtil.sendPostRequest("editInfo", params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String returnData = response.body().string();
                LRReturnJson json = new Gson().fromJson(returnData, LRReturnJson.class);
                Log.w("json", "" + json);
                if (json.getCode().equals("3305")) {
                    listener.onSuccess(json);
                } else {
                    listener.onFail(json);
                }
            }
        });

    }
}
