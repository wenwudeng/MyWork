package com.wenwu.pm.activity.message.bean;

/**
 * @author:wenwudeng
 * @date:21:24 2020/2/18
 * message模块中RecyclerView展示Item Bean
 */
public class MessageListItem {
    private int image;
    private String userId;
    private String chatMessage;
    private String time;

    public MessageListItem(int image, String userId, String chatMessage,String time) {
        this.image = image;
        this.userId = userId;
        this.chatMessage = chatMessage;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
