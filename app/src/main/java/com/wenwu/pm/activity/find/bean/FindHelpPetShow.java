package com.wenwu.pm.activity.find.bean;

/**
 * @author:wenwudeng
 * @date:23:42 2020/2/17
 * 发现模块中,宠友互助功能的问题求助列表展示bean
 */
public class FindHelpPetShow {

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

    public FindHelpPetShow(int qId, int uId, String title, String content,
                           String img, String location, int like, int answer,
                           String time, String photo, String userNam) {
        this.qId = qId;
        this.uId = uId;
        this.title = title;
        this.content = content;
        this.img = img;
        this.location = location;
        this.like = like;
        this.answer = answer;
        this.time = time;
        this.photo = photo;
        this.userNam = userNam;
    }

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


