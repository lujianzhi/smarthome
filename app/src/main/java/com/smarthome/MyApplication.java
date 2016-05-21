package com.smarthome;

import android.app.Application;
import android.app.Notification;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.smarthome.config.MyConstants;
import com.smarthome.entity.User;
import com.smarthome.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

public class MyApplication extends Application {

    public static User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        init();

    }

    public static User getCurrentUser() {
        if (currentUser != null) {
            synchronized (Application.class) {
                if (currentUser != null) {
                    return currentUser;
                }
            }
        }
        return new User();
    }

    private void init() {
        initBaiDuPush();
        initOkHttpUtils();
        ToastUtils.initToastUtils(this);
    }

    private void initOkHttpUtils() {
        OkHttpUtils.getInstance().setConnectTimeout(5000, TimeUnit.MILLISECONDS);
    }

    private void initBaiDuPush() {

        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                MyConstants.BAIDUPUSH_API_KEY);
        Resources resource = this.getResources();
        String pkgName = this.getPackageName();
        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                resource.getIdentifier("notification_custom_builder", "layout", pkgName),
                resource.getIdentifier("notification_icon", "id", pkgName),
                resource.getIdentifier("notification_title", "id", pkgName),
                resource.getIdentifier("notification_text", "id", pkgName));
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(resource.getIdentifier(
                "simple_notification_icon", "drawable", pkgName));
        cBuilder.setNotificationSound(Uri.withAppendedPath(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 推送高级设置，通知栏样式设置为下面的ID
        PushManager.setNotificationBuilder(this, 1, cBuilder);

    }

}
