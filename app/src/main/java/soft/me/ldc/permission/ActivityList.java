package soft.me.ldc.permission;

import android.app.Activity;

import java.util.ArrayList;

public class ActivityList {

    private static ArrayList<Activity> activities = new ArrayList<>();

    //添加activity
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    //移除某个activity
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    //杀死某个activity
    public static void killActivity(Activity activity) {
        if (activity != null)
            activity.finish();
    }

    //杀死全部activity
    public static void killAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }

    //获取最上面的activity
    public static Activity getTopActivity() {
        return activities.get(activities.size() - 1);
    }
}
