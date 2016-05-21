package com.smarthome.MVPpresenter;

import com.smarthome.MVPContract.LoginMVPContract;
import com.smarthome.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/18.
 */
public class LoginPresenter implements LoginMVPContract.ILoginPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginMVPContract.ILoginView loginView;
    private LoginMVPContract.ILoginModel loginModel;

    public LoginPresenter(LoginMVPContract.ILoginModel loginModel, LoginMVPContract.ILoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {

    }

    @Override
    public void verifyUser() {
        loginView.startLoading();
        loginModel.verifyUser(loginView.getUserName(), loginView.getUserPassword()).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loginView.stopLoading();
                LogUtils.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                if (1 == loginModel.parseJsonCode(response)) {
                    loginView.allowLogin();
                } else {
                    loginView.forbidLogin(loginModel.parseJsonMessage(response));
                }
                loginView.stopLoading();
            }
        });
    }
}
