package com.smarthome.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smarthome.R;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.scene_recyclerView)
    RecyclerView sceneRecyclerView;
    @BindView(R.id.warn_recyclerView)
    RecyclerView warnRecyclerView;
    @BindView(R.id.log_message_recyclerView)
    RecyclerView logMessageRecyclerView;

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.app_name;
    }

    @Override
    protected void initPresenterAndModel() {

    }

    @Override
    public void onClick(View v) {

    }
}
