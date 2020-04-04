package com.wenwu.pm.goson;

import java.io.Serializable;

/**
 * @author:wenwudeng
 * @date:10:08 AM 3/20/2020
 */
public class ShowReturnJson implements Serializable {

    private boolean success;
    private String code;
    private String msg;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ShowReturnJson{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}