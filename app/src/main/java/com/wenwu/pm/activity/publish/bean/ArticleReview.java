package com.wenwu.pm.activity.publish.bean;

/**
 * @author:wenwudeng
 * @date:10:17 PM 3/26/2020
 */
public class ArticleReview {
    private int photo;
    private String userId;
    private String time;
    private int likeCount;
    private String reviewContent;

    public ArticleReview(int photo, String userId, String time, int likeCount, String reviewContent) {
        this.photo = photo;
        this.userId = userId;
        this.time = time;
        this.likeCount = likeCount;
        this.reviewContent = reviewContent;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
