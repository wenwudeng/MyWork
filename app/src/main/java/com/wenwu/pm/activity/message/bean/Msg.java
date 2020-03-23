package com.wenwu.pm.activity.message.bean;

/**
 * @author:wenwudeng
 * @date:15:35 2020/2/18
 * 聊天bean
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND =1 ;

    private String content;
    private int type;
    private int image;

    public Msg(String content, int type, int image) {
        this.content = content;
        this.type = type;
        this.image = image;
    }

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
