package com.smarthome.MVPpresenter;

import android.os.Handler;
import android.os.Message;

import com.smarthome.MVPContract.RegisterMVPContract;
import com.smarthome.utils.LogUtils;
import com.smarthome.utils.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.ref.WeakReference;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/19.
 */
public class RegisterPresenter implements RegisterMVPContract.IRegisterPresenter {

    private static final String TAG = RegisterPresenter.class.getSimpleName();

    private RegisterMVPContract.IRegisterModel registerModel;
    private RegisterMVPContract.IRegisterView registerView;

    private MyHandler myHandler;
    private static final int REGISTER = 1001;

    public RegisterPresenter(RegisterMVPContract.IRegisterModel registerModel, RegisterMVPContract.IRegisterView registerView) {
        this.registerModel = registerModel;
        this.registerView = registerView;
        this.myHandler = new MyHandler(this.registerModel, this.registerView, this);
    }

    @Override
    public void register() {
        registerView.startLoading();
        RequestCall call = registerModel.register(registerView.getTelephone(), registerView.getPassword());
        Message message = new Message();
        message.what = RegisterPresenter.REGISTER;
        message.obj = call;
        myHandler.sendMessage(message);
    }

    @Override
    public void initData() {

    }

    private static class MyHandler extends Handler {
        private WeakReference<RegisterMVPContract.IRegisterModel> mRegisterModel;
        private WeakReference<RegisterMVPContract.IRegisterView> mRegisterView;
        private WeakReference<RegisterMVPContract.IRegisterPresenter> mRegisterPresenter;

        public MyHandler(RegisterMVPContract.IRegisterModel mRegisterModel,
                         RegisterMVPContract.IRegisterView mRegisterView,
                         RegisterMVPContract.IRegisterPresenter mRegisterPresenter) {
            this.mRegisterModel = new WeakReference<>(mRegisterModel);
            this.mRegisterView = new WeakReference<>(mRegisterView);
            this.mRegisterPresenter = new WeakReference<>(mRegisterPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            final RegisterMVPContract.IRegisterModel mMmodel = mRegisterModel.get();
            final RegisterMVPContract.IRegisterView mView = mRegisterView.get();
            final RegisterMVPContract.IRegisterPresenter mPresenter = mRegisterPresenter.get();

            if (mPresenter != null) {
                switch (msg.what) {
                    case RegisterPresenter.REGISTER:
                        RequestCall call = (RequestCall) msg.obj;
                        call.execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                LogUtils.e(TAG, e.getMessage());
                                mView.stopLoading();
                            }

                            @Override
                            public void onResponse(String response) {
                                ToastUtils.showShortToast(mMmodel.parseJsonMessage(response));
                                mView.stopLoading();
                            }
                        });
                        break;
                }
            }

        }
    }
}
