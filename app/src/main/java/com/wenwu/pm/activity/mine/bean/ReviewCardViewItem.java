package com.wenwu.pm.activity.mine.bean;

/**
 * @author:wenwudeng
 * @date:3:36 PM 3/19/2020
 */
public class ReviewCardViewItem {
    private int userPhoto;
    private String userNam;
    private String time;
    private String reviewContent;
    private String quesTitle;
    private String favourCount;

    private int showImg;
    private String showContent;

    public ReviewCardViewItem(int userPhoto,String userNam, String time, String reviewContent, String quesTitle, String favourCount) {
        this.userPhoto = userPhoto;
        this.userNam = userNam;
        this.time = time;
        this.reviewContent = reviewContent;
        this.quesTitle = quesTitle;
        this.favourCount = favourCount;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }

    public ReviewCardViewItem(int userPhoto, String userNam, String time, String reviewContent, int showImg, String showContent) {
        this.userPhoto = userPhoto;
        this.userNam = userNam;
        this.time = time;
        this.reviewContent = reviewContent;
        this.showImg = showImg;
        this.showContent = showContent;
    }

    public String getUserNam() {
        return userNam;
    }

    public void setUserNam(String userNam) {
        this.userNam = userNam;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getQuesTitle() {
        return quesTitle;
    }

    public void setQuesTitle(String quesTitle) {
        this.quesTitle = quesTitle;
    }

    public String getFavourCount() {
        return favourCount;
    }

    public void setFavourCount(String favourCount) {
        this.favourCount = favourCount;
    }

    public int getShowImg() {
        return showImg;
    }

    public void setShowImg(int showImg) {
        this.showImg = showImg;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }
}
