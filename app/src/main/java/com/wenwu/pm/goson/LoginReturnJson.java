package com.wenwu.pm.goson;

import java.io.Serializable;

/**
 * @author:wenwudeng
 * @date:10:08 AM 3/20/2020
 */
public class LoginReturnJson implements Serializable {

    private boolean success;
    private String code;
    private String msg;
    private Data data;

    public class Data {
        private int id;
        private String city;
        private String gender;
        private String photo;
        private String password;
        private String phone;
        private String userName;
        private int follow;
        private String time;
        private int collect;
        private int fans;
        private String profile;
        private String pet;
        private boolean status;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
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
                    "id=" + id +
                    ", city='" + city + '\'' +
                    ", gender='" + gender + '\'' +
                    ", photo='" + photo + '\'' +
                    ", password='" + password + '\'' +
                    ", phone='" + phone + '\'' +
                    ", userName='" + userName + '\'' +
                    ", follow=" + follow +
                    ", time='" + time + '\'' +
                    ", collect=" + collect +
                    ", fans=" + fans +
                    ", profile='" + profile + '\'' +
                    ", pet='" + pet + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginReturnJson{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
