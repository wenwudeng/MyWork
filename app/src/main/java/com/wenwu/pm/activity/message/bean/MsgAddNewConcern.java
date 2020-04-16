package com.wenwu.pm.activity.message.bean;

/**
 * @author:wenwudeng
 * @date:11:35 PM 3/8/2020
 */
public class MsgAddNewConcern {
    private String userImage;
    private String userId;
    private String userTime;
    private boolean status;
    private int id;

    public MsgAddNewConcern(String userImage, String userId, String userTime) {
        this.userImage = userImage;
        this.userId = userId;
        this.userTime = userTime;
    }

    public MsgAddNewConcern(String userImage, String userId,boolean status,int id) {
        this.userImage = userImage;
        this.userId = userId;
        this.status = status;
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserImage() {

        return userImage;
    }

    public void setUserImage(String userImage) {
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
