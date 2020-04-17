package com.wenwu.pm.goson;

/**
 * @author:wenwudeng
 * @date:21:49 2020/4/17
 */
public class SupportCollection {
    private boolean success;
    private String code;
    private String msg;
    private Data data;

    public class Data{
        boolean support;
        boolean collection;
        int supportCount;
        int collectionCount;

        public boolean isSupport() {
            return support;
        }

        public void setSupport(boolean support) {
            this.support = support;
        }

        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        public int getSupportCount() {
            return supportCount;
        }

        public void setSupportCount(int supportCount) {
            this.supportCount = supportCount;
        }

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
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
}
