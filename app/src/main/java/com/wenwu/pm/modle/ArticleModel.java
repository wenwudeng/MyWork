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
 * @date:17:14 2020/4/1
 */
public class ArticleModel implements IModel {

    public void publishArticle(int userid, String title, String content, String imgUrl, String location, Listener listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("userid", userid);
        params.put("title", title);
        params.put("content", content);
        if(imgUrl!=null)
        params.put("img", imgUrl);
        params.put("location", location);

        OkHttpUtil.sendPostRequest("article/publish", params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("=========发表长文失败=========");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("=========TEST======");
                String data = response.body().string();
                LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                if (json.getCode().equals("3006")) {
                    listener.onSuccess(json);
                } else {
                    listener.onFail(json);
                }
            }
        });
    }
}
