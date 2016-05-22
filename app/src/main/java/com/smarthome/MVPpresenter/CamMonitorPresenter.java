package com.smarthome.MVPpresenter;

import com.smarthome.MVPContract.CamMonitorMVPContract;

/***
 * Created by Lawson on 2016/5/22.
 */
public class CamMonitorPresenter implements CamMonitorMVPContract.ICamMonitorPresenter {

    private CamMonitorMVPContract.ICamMonitorView camMonitorView;
    private CamMonitorMVPContract.ICamMonitorModel camMonitorModel;

    public CamMonitorPresenter(CamMonitorMVPContract.ICamMonitorView camMonitorView,
                               CamMonitorMVPContract.ICamMonitorModel camMonitorModel) {
        this.camMonitorView = camMonitorView;
        this.camMonitorModel = camMonitorModel;
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {

    }

    @Override
    public void requestCamMonitor() {
        camMonitorView.fillCamMonitorViewData(camMonitorModel.requestCamMonitor());
    }
}
