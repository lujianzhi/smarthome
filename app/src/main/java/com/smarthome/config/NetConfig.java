package com.smarthome.config;

import android.content.Context;

import com.smarthome.R;

public class NetConfig {

    public static String IP;
    public static final String LOCAL = "http://" + IP + "/SmartHome/";
    public static final int CAM_PORT = 8888;

    public static String getUrl(Context context){
        IP = context.getResources().getString(R.string.ip_config);
        return LOCAL;
    }
}
