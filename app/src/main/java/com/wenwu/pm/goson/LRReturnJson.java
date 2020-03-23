package com.wenwu.pm.goson;

/**
 * @author:wenwudeng
 * @date:10:59 AM 3/17/2020
 */
public class LRReturnJson {
    private boolean success;
    private String code;
    private String msg;
    private int data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
