package com.wenwu.pm.activity.message.bean;

/**
 * @author:wenwudeng
 * @date:11:18 PM 3/8/2020
 * 赞和收藏item bean
 */
public class MsgCollectPraise {
    private int userImage;
    private String userId;
    private String userTime;
    private int contentImage;


    public MsgCollectPraise(int userImage, String userId, String userTime, int contentImage) {
        this.userImage = userImage;
        this.userId = userId;
        this.userTime = userTime;
        this.contentImage = contentImage;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public int getContentImage() {
        return contentImage;
    }

    public void setContentImage(int contentImage) {
        this.contentImage = contentImage;
    }
}
