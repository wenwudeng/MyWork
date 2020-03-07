package com.wenwu.pm.find.bean;



/**
 * @author:wenwudeng
 * @date:23:42 2020/2/17
 * 发现模块中,宠友互助功能的问题求助列表展示bean
 */
public class FindHelpPetShow {
    private int userPhoto;
    private String title;
    private String content;
    private String date;
    private int answerCount;

    public FindHelpPetShow(int userPhoto, String title, String content, String date, int answerCount) {
        this.userPhoto = userPhoto;
        this.title = title;
        this.content = content;
        this.date = date;
        this.answerCount = answerCount;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }
}


