package com.wenwu.pm.utils;

import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.LoginReturnJson;
import com.wenwu.pm.goson.ShowReturnJson;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:10:03 PM 3/20/2020
 */
public class JsonUtil {
   public static LoginReturnJson loginJson;
    public static ShowReturnJson showJson;
   public static int userId;

   public static CardViewItemBean bean;//文章初始化

   public static String commentJson;//评论初始化

   public static LRReturnJson json;

   public static List<CardViewItemBean> ItemBeanList;

}
