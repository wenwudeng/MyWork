package com.wenwu.pm.activity.find.activity.hospital;

/**
 * @author:wenwudeng
 * @date:17:37 2020/4/11
 */
public class PetHospitalModel {
    private String storeName;
    private String distance;
    private String address;
    private String workTime;
    private String location;
    private String tel;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String gettel() {
        return tel;
    }

    public void settel(String tel) {
        this.tel = tel;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "PetHospitalModel{" +
                "storeName='" + storeName + '\'' +
                ", distance='" + distance + '\'' +
                ", address='" + address + '\'' +
                ", workTime='" + workTime + '\'' +
                ", location='" + location + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
