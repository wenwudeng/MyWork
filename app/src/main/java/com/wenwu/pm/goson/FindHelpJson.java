package com.wenwu.pm.goson;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:10:15 2020/4/6
 * 宠友互助
 */
public class FindHelpJson {
    private boolean success;
    private String code;
    private String msg;
    private List<Data> data;

    public class Data {
        private  int qId;
        private int uId;
        private String title;
        private String content;
        private String img;
        private String location;
        private int like;
        private int answer;
        private String time;
        private String photo;
        private String userNam;

        public int getqId() {
            return qId;
        }

        public void setqId(int qId) {
            this.qId = qId;
        }

        public int getuId() {
            return uId;
        }

        public void setuId(int uId) {
            this.uId = uId;
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

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getAnswer() {
            return answer;
        }

        public void setAnswer(int answer) {
            this.answer = answer;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUserNam() {
            return userNam;
        }

        public void setUserNam(String userNam) {
            this.userNam = userNam;
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
