package com.wenwu.pm.goson;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:0:00 2020/4/5
 */
public class MyLogJson {
    private boolean success;
    private String code;
    private String msg;
    private List<Data> data;

    public class Data{
        private int id;
        private int userid;
        private String title;
        private String content;
        private String img;
        private int like;
        private int collect;
        private String time;
        private boolean status;
        private String location;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", img='" + img + '\'' +
                    ", like=" + like +
                    ", collect=" + collect +
                    ", time='" + time + '\'' +
                    ", status=" + status +
                    ", location='" + location + '\'' +
                    '}';
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

    @Override
    public String toString() {
        return "MyLogJson{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
