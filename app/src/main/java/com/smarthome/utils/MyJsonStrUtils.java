package com.smarthome.utils;

import com.smarthome.R;

import org.json.JSONException;
import org.json.JSONObject;

/***
 * Created by Lawson on 2016/5/18.
 */
public class MyJsonStrUtils {

    public static int getCode(String responseStr) {
        int code = -1;
        try {
            JSONObject obj = new JSONObject(responseStr);
            if (obj.has("code")) {
                code = obj.getInt("code");
            } else {
                ToastUtils.showShortToast(R.string.json_code_error);
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
                    ToastUtils.showShortToast(obj.getString("message"));
                    return "";
                } else if (code == 1) {
                    if (obj.has("message")) {
                        message = obj.getString("message");
                    } else {
                        ToastUtils.showShortToast(R.string.json_message_error);
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
