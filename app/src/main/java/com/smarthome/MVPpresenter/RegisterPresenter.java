package com.smarthome.MVPpresenter;

import com.smarthome.MVPContract.RegisterMVPContract;
import com.smarthome.utils.LogUtils;
import com.smarthome.utils.MyJsonStrUtils;
import com.smarthome.utils.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/19.
 */
public class RegisterPresenter implements RegisterMVPContract.IRegisterPresenter {

    private static final String TAG = RegisterPresenter.class.getSimpleName();

    private RegisterMVPContract.IRegisterModel registerModel;
    private RegisterMVPContract.IRegisterView registerView;

    public RegisterPresenter(RegisterMVPContract.IRegisterModel registerModel, RegisterMVPContract.IRegisterView registerView) {
        this.registerModel = registerModel;
        this.registerView = registerView;
    }

    @Override
    public void register() {
        registerView.startLoading();
        registerModel.register(registerView.getUserName(),
                registerView.getTelephone(),
                registerView.getPassword())
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        LogUtils.e(TAG, e.getMessage());
                        registerView.stopLoading();
                    }

                    @Override
                    public void onResponse(String response) {
                        registerView.stopLoading();
                        if (1 == MyJsonStrUtils.getCode(response)) {
                            registerView.backToLogin();
                        }
                    }
                });
        ;

    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {

    }
}
