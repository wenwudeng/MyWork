package com.wenwu.pm.modle;

import com.google.gson.Gson;
import com.wenwu.pm.goson.Msg;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.RequestBody;

/**
 * @author:wenwudeng
 * @date:1:25 PM 3/17/2020
 */
public class UserImpl implements User{
    private Gson gson = new Gson();

    @Override
    public void login(final String mapping, final String phone, final String password) throws IOException {

    }

    @Override
    public void register(String phone, String password) {

    }

    @Override
    public void sendCode(String code) {

    }
}
