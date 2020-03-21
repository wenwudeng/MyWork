package com.wenwu.pm.goson;

import java.io.Serializable;

/**
 * @author:wenwudeng
 * @date:12:13 AM 3/20/2020
 */
public class Data implements Serializable{
    private String city;
    private String gender;
    private String photo;
    private String userName;
    private int follow;
    private String time;
    private int collectLike;
    private int fans;
    private String profile;
    private String pet;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCollectLike() {
        return collectLike;
    }

    public void setCollectLike(int collectLike) {
        this.collectLike = collectLike;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Data{" +
                "city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", photo=" + photo +
                ", userName='" + userName + '\'' +
                ", follow=" + follow +
                ", time='" + time + '\'' +
                ", collectLike=" + collectLike +
                ", fans=" + fans +
                ", profile='" + profile + '\'' +
                ", pet='" + pet + '\'' +
                '}';
    }
}