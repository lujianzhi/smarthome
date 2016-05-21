package com.smarthome.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.MVPmodel.MainModel;
import com.smarthome.MVPpresenter.MainPresenter;
import com.smarthome.R;
import com.smarthome.adapter.OperationLogAdapter;
import com.smarthome.adapter.SceneAdapter;
import com.smarthome.entity.OperationLog;
import com.smarthome.entity.Scene;

import java.util.List;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class MainActivity extends BaseActivity implements MainMVPContract.IMainView {

    @BindView(R.id.scene_recyclerView)
    RecyclerView sceneRecyclerView;
    @BindView(R.id.warn_recyclerView)
    RecyclerView warnRecyclerView;
    @BindView(R.id.log_message_recyclerView)
    RecyclerView logMessageRecyclerView;
    @BindView(R.id.more_scene)
    TextView moreScene;

    private MainMVPContract.IMainPresenter mainPresenter;

    private SceneAdapter sceneAdapter;
    private OperationLogAdapter warnLogAdapter;
    private OperationLogAdapter logMessageAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        mainPresenter.clearData();
        mainPresenter.requestSceneList();
        mainPresenter.requestWarnInfoList();
        mainPresenter.requestLogMessageList();
    }

    @Override
    protected void initViews() {
        sceneAdapter = new SceneAdapter(this);
        sceneRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        sceneRecyclerView.setAdapter(sceneAdapter);

        warnLogAdapter = new OperationLogAdapter();
        warnRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        warnRecyclerView.setAdapter(warnLogAdapter);

        logMessageAdapter = new OperationLogAdapter();
        logMessageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        logMessageRecyclerView.setAdapter(logMessageAdapter);

        moreScene.setOnClickListener(this);
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
        mainPresenter = new MainPresenter(new MainModel(), this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.more_scene:
                startActivity(new Intent(this, SceneActivity.class));
                break;
        }
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
    public void setSceneList(List<Scene> sceneList) {
        sceneAdapter.setSceneList(sceneList);
        sceneAdapter.notifyDataSetChanged();
    }

    @Override
    public void setWarnInfoList(List<OperationLog> warnInfoList) {
        warnLogAdapter.setOperationLogList(warnInfoList);
        warnLogAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLogInfoList(List<OperationLog> logInfoList) {
        logMessageAdapter.setOperationLogList(logInfoList);
        logMessageAdapter.notifyDataSetChanged();
    }
}
