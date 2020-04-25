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
    private String image;

    public Msg(String content, int type, String image) {
        this.content = content;
        this.type = type;
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "content='" + content + '\'' +
                ", type=" + type +
                ", image='" + image + '\'' +
                '}';
    }
}
