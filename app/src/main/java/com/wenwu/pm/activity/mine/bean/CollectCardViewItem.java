package com.wenwu.pm.activity.mine.bean;

/**
 * @author:wenwudeng
 * @date:12:05 PM 3/19/2020
 */
public class CollectCardViewItem {
    private String title;
    private int photo;
    private String userName;
    private String recommendCount;
    private String likeCount;

    public CollectCardViewItem(String title, int photo, String userName, String recommendCount, String likeCount) {
        this.title = title;
        this.photo = photo;
        this.userName = userName;
        this.recommendCount = recommendCount;
        this.likeCount = likeCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(String recommendCount) {
        this.recommendCount = recommendCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
