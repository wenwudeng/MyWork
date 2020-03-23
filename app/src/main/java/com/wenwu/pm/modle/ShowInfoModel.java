package com.wenwu.pm.modle;

import com.google.gson.Gson;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.ShowReturnJson;
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
 * @date:9:27 PM 3/23/2020
 * 显示个人信息
 */
public class ShowInfoModel implements IModel {
    public void showInfo(int userId, Listener listener) {
        Map<String,Object> param = new HashMap<>();
        param.put("user_id", userId);
        OkHttpUtil.sendPostRequest("get", param, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                ShowReturnJson json = new Gson().fromJson(responseData, ShowReturnJson.class);
                if (json.getCode().equals("0000")) {
                    listener.onSuccess(json);
                } else {
                    listener.onFail(json);
                }
            }
        });
    }
}
