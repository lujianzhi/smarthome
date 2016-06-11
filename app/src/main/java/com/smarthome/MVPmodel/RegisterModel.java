package com.smarthome.MVPmodel;

import android.content.Context;

import com.smarthome.MVPContract.RegisterMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

/***
 * Created by Lawson on 2016/5/19.
 */
public class RegisterModel implements RegisterMVPContract.IRegisterModel {
    @Override
    public RequestCall register(Context context, String userName, String telephone, String password) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "login_register.action")
                .addParams("user.userName", userName)
                .addParams("user.telephone", telephone)
                .addParams("user.password", password)
                .build();
    }

    @Override
    public int parseJsonCode(String jsonStr) {
        return MyJsonStrUtils.getCode(jsonStr);
    }

    @Override
    public String parseJsonMessage(String jsonStr) {
        return MyJsonStrUtils.getMessage(jsonStr);
    }

    @Override
    public void clearData() {

    }
}
