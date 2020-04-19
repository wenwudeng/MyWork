package com.wenwu.pm.activity.mine.bean;

/**
 * @author:wenwudeng
 * @date:3:36 PM 3/19/2020
 */
public class ReviewCardViewItem {

    private String authorName;
    private String authorPhoto;
    private String userPhoto;
    private String userNam;
    private String cTime;
    private String commentContent;
    private String ArtTitle;
    private int articleId;
    private int cLike;
    private int aLike;
    private String articleContent;
    private String location;
    private String img;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ReviewCardViewItem() { }

    public ReviewCardViewItem(String authorName,String authorPhoto,int userId,String userPhoto, String userNam, String cTime,
                              String commentContent, String artTitle, int articleId,
                              int cLike, int aLike, String articleContent, String location,
                              String img) {
        this.authorName = authorName;
        this.authorPhoto = authorPhoto;
        this.userId = userId;
        this.userPhoto = userPhoto;
        this.userNam = userNam;
        this.cTime = cTime;
        this.commentContent = commentContent;
        ArtTitle = artTitle;
        this.articleId = articleId;
        this.cLike = cLike;
        this.aLike = aLike;
        this.articleContent = articleContent;
        this.location = location;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserNam() {
        return userNam;
    }

    public void setUserNam(String userNam) {
        this.userNam = userNam;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getArtTitle() {
        return ArtTitle;
    }

    public void setArtTitle(String artTitle) {
        ArtTitle = artTitle;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getcLike() {
        return cLike;
    }

    public void setcLike(int cLike) {
        this.cLike = cLike;
    }

    public int getaLike() {
        return aLike;
    }

    public void setaLike(int aLike) {
        this.aLike = aLike;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
