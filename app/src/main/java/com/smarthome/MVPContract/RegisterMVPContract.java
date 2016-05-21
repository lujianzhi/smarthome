package com.smarthome.MVPContract;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.zhy.http.okhttp.request.RequestCall;

/***
 * Created by Lawson on 2016/5/19.
 */
public interface RegisterMVPContract {

    interface IRegisterView extends IBaseView {

        /**
         * 获取用户名
         */
        String getUserName();

        /**
         * 获取手机号
         */
        String getTelephone();

        /**
         * 获取密码
         */
        String getPassword();

        /**
         * 注册成功
         */
        void backToLogin();
    }

    interface IRegisterPresenter extends IBasePresenter {

        /**
         * 注册
         */
        void register();
    }

    interface IRegisterModel extends IBaseModel {

        /**
         * 注册
         */
        RequestCall register(String userName, String telephone, String password);
    }

}
