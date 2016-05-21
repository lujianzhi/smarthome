package com.smarthome.utils;

import com.smarthome.R;

import org.json.JSONException;
import org.json.JSONObject;

/***
 * Created by Lawson on 2016/5/18.
 */
public class MyJsonStrUtils {

    private static final String TAG = "后台JSON异常";

    public static int getCode(String responseStr) {
        int code = -1;
        try {
            JSONObject obj = new JSONObject(responseStr);
            if (obj.has("code")) {
                code = obj.getInt("code");
            } else {
                LogUtils.e(TAG, "json_code格式不正确");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String getMessage(String responseStr) {
        String message = "";
        try {
            JSONObject obj = new JSONObject(responseStr);
            if (obj.has("code")) {
                int code = obj.getInt("code");
                if (code == 0) {
                    // 弹出显示错误信息
                    LogUtils.e(TAG, obj.getString("message"));
                    return obj.getString("message");
                } else if (code == 1) {
                    if (obj.has("message")) {
                        message = obj.getString("message");
                    } else {
                        LogUtils.e(TAG, "json_message格式不正确");
                    }
                }
            } else {
                ToastUtils.showShortToast(R.string.json_code_error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }
}
