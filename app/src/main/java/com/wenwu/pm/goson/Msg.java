package com.wenwu.pm.goson;

/**
 * @author:wenwudeng
 * @date:10:59 AM 3/17/2020
 */
public class Msg {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
