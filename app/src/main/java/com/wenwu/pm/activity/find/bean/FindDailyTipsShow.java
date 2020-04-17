package com.wenwu.pm.activity.find.bean;

/**
 * @author:wenwudeng
 * @date:14:48 2020/2/17
 *每日贴士信息bean
 */

public class FindDailyTipsShow {
    private int aId;
    private int authorId;
    private String authorName;
    private String authorPhoto;
    private String title;
    private String content;
    private String image;
    private int learnCount;

    public FindDailyTipsShow(int aId, int authorId, String authorName, String authorPhoto,
                             String title, String content, String image, int learnCount) {
        this.aId = aId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorPhoto = authorPhoto;
        this.title = title;
        this.content = content;
        this.image = image;
        this.learnCount = learnCount;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLearnCount() {
        return learnCount;
    }

    public void setLearnCount(int learnCount) {
        this.learnCount = learnCount;
    }
}
