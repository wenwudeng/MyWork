package com.wenwu.pm.goson;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:13:21 2020/4/5
 */
public class MyCommentJson {

    private boolean success;
    private String code;
    private String msg;
    private List<Data> data;

    public class Data {
        private int aid;
        private String title;
        private String content;
        private String img;
        private String location;
        private int collect;
        private String ctime;
        private String ccontent;
        private int alike;
        private int clike;


        public int getAid() {
            return aid;
        }


        public void setAid(int aid) {
            this.aid = aid;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public String getCTime() {
            return ctime;
        }

        public void setCTime(String ctime) {
            this.ctime = ctime;
        }

        public String getCcontent() {
            return ccontent;
        }

        public void setCcontent(String ccontent) {
            this.ccontent = ccontent;
        }

        public int getAlike() {
            return alike;
        }

        public void setAlike(int alike) {
            this.alike = alike;
        }

        public int getClike() {
            return clike;
        }

        public void setClike(int clike) {
            this.clike = clike;
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
