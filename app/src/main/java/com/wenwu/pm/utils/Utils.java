package com.wenwu.pm.utils;

import java.text.DecimalFormat;

/**
 * @author:wenwudeng
 * @date:16:11 2020/4/12
 */
public class Utils {
    public static String distanceParse(String distance) {
        DecimalFormat df = new DecimalFormat("######0.00");
        Double dis = Double.parseDouble(distance);

        return df.format(dis / 1000.0);
    }

    public static String phone(String str) {
        if (str.length() > 4) {
            String phone = str.substring(0, 11);
            return phone;
        }
        return "17859735572";
    }

    public static String addressFilter(String address) {
        if (address.equals("[]")) {
            return "地址不详";
        }
        return address;
    }



}
