package com.wenwu.pm.goson;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:14:22 2020/4/19
 */
public class UserCollections {
    private boolean success;
    private String code;
    private String msg;
    private List<Data> data;

    public class Data{
        private int authorId;
        private String authorName;
        private String authorPhoto;
        private int aid;
        private String title;
        private String content;
        private String img;
        private int like;
        private int comment;

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getAuthorPhoto() {
            return authorPhoto;
        }

        public void setAuthorPhoto(String authorPhoto) {
            this.authorPhoto = authorPhoto;
        }

        public int getaId() {
            return aid;
        }

        public void setaId(int aId) {
            this.aid = aId;
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

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
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
