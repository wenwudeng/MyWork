package com.wenwu.pm.activity.message.bean;

/**
 * @author:wenwudeng
 * @date:11:35 PM 3/8/2020
 */
public class MsgAddNewConcern {
    private int userImage;
    private String userId;
    private String userTime;

    public MsgAddNewConcern(int userImage, String userId, String userTime) {
        this.userImage = userImage;
        this.userId = userId;
        this.userTime = userTime;
    }

    public MsgAddNewConcern(int userImage, String userId) {
        this.userImage = userImage;
        this.userId = userId;
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
}
