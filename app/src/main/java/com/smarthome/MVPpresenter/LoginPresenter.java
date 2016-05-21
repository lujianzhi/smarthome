package com.smarthome.MVPpresenter;

import android.os.Handler;
import android.os.Message;

import com.smarthome.MVPContract.LoginMVPContract;
import com.smarthome.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.ref.WeakReference;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/18.
 */
public class LoginPresenter implements LoginMVPContract.ILoginPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginMVPContract.ILoginView loginView;
    private LoginMVPContract.ILoginModel loginModel;

    private final MyHandler myHandler;
    private static final int VERIFY_USER = 1001;

    public LoginPresenter(LoginMVPContract.ILoginModel loginModel, LoginMVPContract.ILoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.myHandler = new MyHandler(loginModel, loginView, this);
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
        RequestCall call = loginModel.verifyUser(loginView.getUserName(), loginView.getUserPassword());
        Message message = new Message();
        message.what = LoginPresenter.VERIFY_USER;
        message.obj = call;
        myHandler.sendMessage(message);
    }

    private static class MyHandler extends Handler {
        //使用弱引用
        private final WeakReference<LoginMVPContract.ILoginPresenter> mPresenter;
        private final WeakReference<LoginMVPContract.ILoginModel> mModel;
        private final WeakReference<LoginMVPContract.ILoginView> mView;

        public MyHandler(LoginMVPContract.ILoginModel mModel, LoginMVPContract.ILoginView mView,
                         LoginMVPContract.ILoginPresenter mPresenter) {
            this.mModel = new WeakReference<>(mModel);
            this.mView = new WeakReference<>(mView);
            this.mPresenter = new WeakReference<>(mPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            final LoginMVPContract.ILoginModel model = this.mModel.get();
            final LoginMVPContract.ILoginView view = this.mView.get();
            final LoginMVPContract.ILoginPresenter presenter = this.mPresenter.get();
            if (presenter != null) {
                switch (msg.what) {
                    case LoginPresenter.VERIFY_USER:
                        RequestCall call = (RequestCall) msg.obj;
                        call.execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                view.stopLoading();
                                LogUtils.e(TAG, e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                if (1 == model.parseJsonCode(response)) {
                                    view.allowLogin();
                                } else {
                                    view.forbidLogin(model.parseJsonMessage(response));
                                }
                                view.stopLoading();
                            }
                        });
                        break;
                }
            }

        }
    }
}
