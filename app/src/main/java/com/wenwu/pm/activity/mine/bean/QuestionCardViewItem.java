package com.wenwu.pm.activity.mine.bean;

/**
 * @author:wenwudeng
 * @date:10:44 AM 3/19/2020
 */
public class QuestionCardViewItem {
    private String title;
    private String time;
    private int answerCount;


    private int id;
    private int userid;
    private String content;
    private String img;
    private String location;
    private int like;
    private int collect;

    public QuestionCardViewItem(String title, String time, int answerCount) {
        this.title = title;
        this.time = time;
        this.answerCount = answerCount;
    }

    public QuestionCardViewItem(String title, String time, int answerCount, int id, int userid,
                                String content, String img, String location, int like, int collect) {
        this.title = title;
        this.time = time;
        this.answerCount = answerCount;
        this.id = id;
        this.userid = userid;
        this.content = content;
        this.img = img;
        this.location = location;
        this.like = like;
        this.collect = collect;
    }

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

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    @Override
    public String toString() {
        return "QuestionCardViewItem{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", answerCount=" + answerCount +
                ", id=" + id +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", location='" + location + '\'' +
                ", like=" + like +
                ", collect=" + collect +
                '}';
    }
}
