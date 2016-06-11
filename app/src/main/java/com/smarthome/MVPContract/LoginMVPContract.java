package com.smarthome.MVPContract;

import android.content.Context;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.zhy.http.okhttp.request.RequestCall;

/***
 * Created by Lawson on 2016/5/18.
 */
public interface LoginMVPContract {

    interface ILoginView extends IBaseView {

        /**
         * 用户名
         */
        String getUserName();

        /**
         * 用户密码
         */
        String getUserPassword();

        /**
         * 允许登陆
         */
        void allowLogin();

        /**
         * 不允许登陆
         */
        void forbidLogin(String str);
    }

    interface ILoginPresenter extends IBasePresenter {

        /**
         * 验证用户
         */
        void verifyUser();
    }

    interface ILoginModel extends IBaseModel {

        /**
         * 验证用户
         */
        RequestCall verifyUser(Context context, String userName, String password);

    }

}
