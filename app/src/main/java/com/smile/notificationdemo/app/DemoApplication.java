package com.smile.notificationdemo.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Smile Wei
 * @since 2017/8/5.
 */

public class DemoApplication extends Application {
    private static DemoApplication application;

    private List<Activity> list = Collections.synchronizedList(new LinkedList<Activity>());

    public static DemoApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        registerActivityListener();
    }

    /**
     * add the activity
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (activity == null)
            return;
        list.add(activity);
    }

    /**
     * remove the activity
     *
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        if (activity == null)
            return;
        list.remove(activity);
    }

    /**
     * get current activity
     *
     * @return activity
     */
    public Activity currentActivity() {
        if (list == null || list.isEmpty())
            return null;
        return list.get(list.size() - 1);
    }

    /**
     * finish the given activity
     *
     * @param activity activity
     */
    public void finishActivity(Activity activity) {
        if (list == null || list.isEmpty())
            return;
        if (activity == null)
            return;
        removeActivity(activity);
        activity.finish();
    }

    /**
     * finish the current activity(top activity)
     */
    public void finishCurrentActivity() {
        if (list == null || list.isEmpty())
            return;
        finishActivity(list.get(list.size() - 1));
    }

    /**
     * check if all activity had bean finished
     *
     * @return true or false
     */
    public boolean isAllActivityFinished() {
        return list == null || list.isEmpty();
    }

    private void registerActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }

}
