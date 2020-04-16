package com.wenwu.pm.activity.home.bean;

/**
 * 此类用于用户发布信息bean
 */
public class CardViewItemBean {
    private int userId;
    private int articleId;
    private String title;
    private int sendImageId;
    private String imgUrl;
    private String content;
    private String userName;
    private String userPhoto;
    private int acceptFavourCount;
    private int collect;

    public CardViewItemBean(int userId,int articleId, String title, String imgUrl, String content,
                            String userName, String userPhoto, int acceptFavourCount) {
        this.userId = userId;
        this.articleId = articleId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.acceptFavourCount = acceptFavourCount;
    }

    public CardViewItemBean(int articleId, String title, String imgUrl, String content,
                            String userName, String userPhoto, int acceptFavourCount) {
        this.articleId = articleId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.acceptFavourCount = acceptFavourCount;
    }


    public CardViewItemBean(int articleId, String title, String imgUrl, String content, int acceptFavourCount, int collect) {
        this.articleId = articleId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.acceptFavourCount = acceptFavourCount;
        this.collect = collect;
    }

    public CardViewItemBean(int userId,int articleId, String title, String imgUrl, String content, int acceptFavourCount, int collect) {
        this.userId = userId;
        this.articleId = articleId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.content = content;
        this.acceptFavourCount = acceptFavourCount;
        this.collect = collect;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getAcceptFavourCount() {
        return acceptFavourCount;
    }

    public void setAcceptFavourCount(int acceptFavourCount) {
        this.acceptFavourCount = acceptFavourCount;
    }

    @Override
    public String toString() {
        return "CardViewItemBean{" +
                "title='" + title + '\'' +
                ", sendImageId=" + sendImageId +
                ", imgUrl='" + imgUrl + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", acceptFavourCount=" + acceptFavourCount +
                '}';
    }
}
