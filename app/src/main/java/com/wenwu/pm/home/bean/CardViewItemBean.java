package com.wenwu.pm.home.bean;

/**
 * 此类用于用户发布信息bean
 */
public class CardViewItemBean {
    private String title;
    private int sendImageId;
    private String content;
    private String userId;
    private int userPhoto;
    private int acceptFavourCount ;

    public CardViewItemBean(String title, int sendImage, String content, String userId, int userPhoto, int acceptFavourCount) {
        this.title = title;
        this.sendImageId = sendImage;
        this.content = content;
        this.userId = userId;
        this.userPhoto = userPhoto;
        this.acceptFavourCount = acceptFavourCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSendImageId() {
        return sendImageId;
    }

    public void setSendImageId(int sendImage) {
        this.sendImageId = sendImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getAcceptFavourCount() {
        return acceptFavourCount;
    }

    public void setAcceptFavourCount(int acceptFavourCount) {
        this.acceptFavourCount = acceptFavourCount;
    }
}
