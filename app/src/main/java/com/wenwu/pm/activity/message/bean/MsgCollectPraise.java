package com.wenwu.pm.activity.message.bean;

/**
 * @author:wenwudeng
 * @date:11:18 PM 3/8/2020
 * 赞和收藏item bean
 */
public class MsgCollectPraise {
    private int sId;
    private String sPhoto;
    private String sName;
    private int aId;
    private int auId;
    private String title;
    private String content;
    private String img;
    private String time;

    public MsgCollectPraise(int sId, String sPhoto, String sName,
                            int aId, int auId, String title, String content, String img,String time) {
        this.sId = sId;
        this.sPhoto = sPhoto;
        this.sName = sName;
        this.aId = aId;
        this.auId = auId;
        this.title = title;
        this.content = content;
        this.img = img;
        this.time =time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsPhoto() {
        return sPhoto;
    }

    public void setsPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getAuId() {
        return auId;
    }

    public void setAuId(int auId) {
        this.auId = auId;
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

    @Override
    public String toString() {
        return "MsgCollectPraise{" +
                "sId=" + sId +
                ", sPhoto='" + sPhoto + '\'' +
                ", sName='" + sName + '\'' +
                ", aId=" + aId +
                ", auId=" + auId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
