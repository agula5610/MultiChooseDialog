package com.luxiaochun.multiselectiondialog.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

public class ScreenUtil {
    /*屏幕的高度*/
    public static int getScreenHeigth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        return manager.getDefaultDisplay().getHeight();
//        return px2dp(activity, height);
    }

    public static int getScreenWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        return manager.getDefaultDisplay().getWidth();
//        return px2dp(activity, height);
    }

    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
