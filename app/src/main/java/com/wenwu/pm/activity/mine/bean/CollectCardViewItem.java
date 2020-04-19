package com.wenwu.pm.activity.mine.bean;

/**
 * @author:wenwudeng
 * @date:12:05 PM 3/19/2020
 */
public class CollectCardViewItem {
    private int authorId;
    private String authorName;
    private String authorPhoto;
    private int aId;
    private String title;
    private String content;
    private String img;
    private int like;
    private int comment;

    public CollectCardViewItem(int authorId, String authorName, String authorPhoto,
                               int aId, String title, String content, String img, int like,
                               int comment) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorPhoto = authorPhoto;
        this.aId = aId;
        this.title = title;
        this.content = content;
        this.img = img;
        this.like = like;
        this.comment = comment;
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

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
