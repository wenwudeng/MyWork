package com.wenwu.pm.activity.find.bean;

/**
 * @author:wenwudeng
 * @date:12:28 2020/2/18
 * 热议话题显示bean
 */
public class FindHotTopicShow {
    private int image;
    private String takeCount;
    private String topic;
    private String topicDescription;

    public FindHotTopicShow(int image, String takeCount, String topic, String topicDescription) {
        this.image = image;
        this.takeCount = takeCount;
        this.topic = topic;
        this.topicDescription = topicDescription;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(String takeCount) {
        this.takeCount = takeCount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }
}
