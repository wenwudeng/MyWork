package com.wenwu.pm.activity.mine.bean;

/**
 * @author:wenwudeng
 * @date:10:44 AM 3/19/2020
 */
public class QuestionCardViewItem {
    private String title;
    private String time;
    private String answerCount;

    public QuestionCardViewItem(String title, String time, String answerCount) {
        this.title = title;
        this.time = time;
        this.answerCount = answerCount;
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

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }
}
