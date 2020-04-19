package com.wenwu.pm.goson;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:12:19 2020/4/19
 */
public class BeSupportInfo {
    private boolean success;
    private String code;
    private String msg;
    private List<Data> data;

    public class Data {
        private int sid;
        private String sphoto;
        private String sname;
        private int aid;
        private int auId;
        private String title;
        private String content;
        private String img;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSphoto() {
            return sphoto;
        }

        public void setSphoto(String sphoto) {
            this.sphoto = sphoto;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getAuId() {
            return auId;
        }

        public void setAuId(int auId) {
            this.auId = auId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
