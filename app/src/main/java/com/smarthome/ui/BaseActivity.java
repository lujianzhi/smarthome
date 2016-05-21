package com.smarthome.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smarthome.R;
import com.smarthome.widget.MyProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * Created by Lawson on 2016/5/15.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.top_title)
    TextView topTitle;

    protected MyProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    protected void init() {
        initIntentData();
        initPresenterAndModel();
        initView();
    }


    private void initView() {
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        } else {
            throw new RuntimeException("orz...忘了设置layout了...");
        }
        ButterKnife.bind(this);
        initTopTitle(getTopTitleId());
        initViews();
    }

    /**
     * 初始化顶部View
     */
    protected void initTopTitle(int resId) {
        topTitle.setText(resId);
    }

    protected void showProgressDialog() {
        if (dialog == null) {
            dialog = new MyProgressDialog(this);
        }
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    protected void disMissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 初始化View
     */
    protected abstract void initViews();

    /**
     * 设置布局文件id
     */
    protected abstract int getLayoutId();

    /**
     * 设置布局文件id
     */
    protected abstract int getTopTitleId();

    /**
     * 初始化Intent传过来的数据
     */
    protected void initIntentData() {
    }

    /**
     * 初始化Presenter和Model层
     */
    protected abstract void initPresenterAndModel();
}
