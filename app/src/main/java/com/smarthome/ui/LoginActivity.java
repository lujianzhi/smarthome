package com.smarthome.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.MVPContract.LoginMVPContract;
import com.smarthome.MVPmodel.LoginModel;
import com.smarthome.MVPpresenter.LoginPresenter;
import com.smarthome.R;
import com.smarthome.utils.ToastUtils;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginMVPContract.ILoginView {

    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    ButtonRectangle login;
    @BindView(R.id.user_name)
    EditText user_name;
    @BindView(R.id.user_password)
    EditText user_password;

    private LoginMVPContract.ILoginPresenter mPresenter;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register:
                register();
                break;
            case R.id.login:
                mPresenter.verifyUser();
                break;
        }
    }

    private void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void initViews() {
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.button_login;
    }

    @Override
    protected void initPresenterAndModel() {
        mPresenter = new LoginPresenter(new LoginModel(), this);
    }

    @Override
    public void startLoading() {
        showProgressDialog();
    }

    @Override
    public void stopLoading() {
        disMissProgressDialog();
    }

    @Override
    public void notifyDataChanged() {
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getUserName() {
        return user_name.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return user_password.getText().toString();
    }

    @Override
    public void allowLogin() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void forbidLogin(String str) {
        ToastUtils.showShortToast(str);
    }
}
