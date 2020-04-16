package com.wenwu.pm.utils;

import com.wenwu.pm.activity.find.activity.hospital.PetHospitalModel;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.goson.FindHelpJson;
import com.wenwu.pm.goson.LoginReturnJson;
import com.wenwu.pm.goson.MyCommentJson;
import com.wenwu.pm.goson.MyLogJson;
import com.wenwu.pm.goson.MyQuestionJson;


import java.util.ArrayList;
import java.util.List;


/**
 * @author:wenwudeng
 * @date:10:03 PM 3/20/2020
 */
public class JsonUtil {
   //保存Gson解析的登录信息
   public static LoginReturnJson loginJson;

   public static LoginReturnJson personHome;

   //item数据初始化
   public static CardViewItemBean bean;//文章初始化

   //保存未经Gson解析的String型Json数据
   public static String commentJson;//评论初始化

   //获取评论Id
   public static int commentId;

   //保存Gson解析的个人日志数据
   public static MyLogJson myLogJson;

   //保存Gson解析的个人提问数据
   public static MyQuestionJson myQuestionJson;


   //保存提问页面数据
  // public static QuestionCardViewItem questionJson;

   //保存Gson解析的提问数据
   public static MyCommentJson myCommentJson;

   //保存Gson解析后的宠友互助数据
   public static FindHelpJson findHelpJson;
}
