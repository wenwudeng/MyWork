package com.wenwu.pm.modle;

import java.io.IOException;

/**
 * @author:wenwudeng
 * @date:12:56 PM 3/17/2020
 */
public interface User {
    public static final String user = "http://10.0.2.2:8080/api/user/";

    void login(String mapping, String account, String password) throws IOException;

    void register(String phone, String password);

    void sendCode(String code);
}
