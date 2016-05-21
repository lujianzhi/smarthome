package com.smarthome.MVPmodel;

import com.smarthome.MVPContract.LoginMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

/***
 * Created by Lawson on 2016/5/18.
 */
public class LoginModel implements LoginMVPContract.ILoginModel {

    @Override
    public RequestCall verifyUser(String userName, String password) {
        return OkHttpUtils
                .post()
                .url(NetConfig.LOCAL + "login_login.action")
                .addParams("user.userName", userName)
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
