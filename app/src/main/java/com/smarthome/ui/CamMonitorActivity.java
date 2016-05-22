package com.smarthome.ui;

import android.content.Context;
import android.view.View;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.MVPContract.CamMonitorMVPContract;
import com.smarthome.MVPmodel.CamMonitorModel;
import com.smarthome.MVPpresenter.CamMonitorPresenter;
import com.smarthome.R;
import com.smarthome.cammonitor.config.CamMonitorParameter;
import com.smarthome.cammonitor.view.CamMonitorView;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/22.
 */
public class CamMonitorActivity extends BaseActivity implements CamMonitorMVPContract.ICamMonitorView {
    @BindView(R.id.exit_cam_monitor)
    ButtonRectangle exitCamMonitor;
    @BindView(R.id.cmView)
    CamMonitorView cmView;

    private CamMonitorMVPContract.ICamMonitorPresenter camMonitorPresenter;

    @Override
    protected void initViews() {
        camMonitorPresenter.requestCamMonitor();
        exitCamMonitor.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cam_monitor;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.cam_monitor;
    }

    @Override
    protected void initPresenterAndModel() {
        camMonitorPresenter = new CamMonitorPresenter(this, new CamMonitorModel());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_cam_monitor:
                cmView.setRunning(false);
                finish();
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
    public void fillCamMonitorViewData(CamMonitorParameter camMonitorParameter) {
        cmView.setCmPara(camMonitorParameter);
    }
}
