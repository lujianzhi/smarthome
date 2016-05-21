package com.smarthome.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.MVPContract.RegisterMVPContract;
import com.smarthome.MVPmodel.RegisterModel;
import com.smarthome.MVPpresenter.RegisterPresenter;
import com.smarthome.R;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class RegisterActivity extends BaseActivity implements RegisterMVPContract.IRegisterView {

    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_password)
    EditText userPassword;
    @BindView(R.id.user_password_again)
    EditText userPasswordAgain;
    @BindView(R.id.register)
    ButtonRectangle register;

    private RegisterMVPContract.IRegisterPresenter registerPresenter;

    @Override
    protected void initViews() {
        register.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.button_register;
    }

    @Override
    protected void initPresenterAndModel() {
        registerPresenter = new RegisterPresenter(new RegisterModel(), this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register:
                registerPresenter.register();
                break;
        }
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getTelephone() {
        return userPhone.getText().toString();
    }

    @Override
    public String getPassword() {
        return userPassword.getText().toString();
    }

    @Override
    public void backToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
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
}
