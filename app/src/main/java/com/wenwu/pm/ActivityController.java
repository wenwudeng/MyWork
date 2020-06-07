package com.wenwu.pm;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenwudeng
 * @date:23:40 2020/6/7
 */
public class ActivityController {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
