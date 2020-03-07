package com.wenwu.pm.find.bean;

/**
 * @author:wenwudeng
 * @date:14:48 2020/2/17
 *每日贴士信息bean
 */

public class FindDailyTipsShow {
    private String title;
    private String content;
    private int image;
    private int learnCount;

    public FindDailyTipsShow(String title, String content, int image, int learnCount) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.learnCount = learnCount;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLearnCount(int learnCount) {
        this.learnCount = learnCount;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
    }

    public int getLearnCount() {
        return learnCount;
    }
}
