package com.wenwu.pm.activity.publish.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by moos on 2018/4/20.
 */

public class CommentBean implements Serializable {
    private boolean success;
    private String code;
    private String msg;
    private Data data;

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }


    public class Data implements Serializable{
        private int total;
        private List<CommentDetailBean> list;

        public void setTotal(int total) {
            this.total = total;
        }
        public int getTotal() {
            return total;
        }

        public void setList(List<CommentDetailBean> list) {
            this.list = list;
        }
        public List<CommentDetailBean> getList() {
            return list;
        }
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
