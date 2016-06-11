package com.smarthome.MVPpresenter;

import android.content.Context;

import com.smarthome.AsyncTask.GetEquipmentListTask;
import com.smarthome.AsyncTask.GetOperationLogListTask;
import com.smarthome.AsyncTask.GetSceneListTask;
import com.smarthome.MVPContract.LogMVPContract;
import com.smarthome.entity.OperationLog;
import com.smarthome.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/21.
 */
public class LogPresenter implements LogMVPContract.ILogPresenter {
    private final String TAG = LoginPresenter.class.getSimpleName();

    private LogMVPContract.ILogView logView;
    private LogMVPContract.ILogModel logModel;

    public LogPresenter(LogMVPContract.ILogView logView, LogMVPContract.ILogModel logModel) {
        this.logView = logView;
        this.logModel = logModel;
    }

    @Override
    public void requestAllScene() {
        logView.startLoading();
        logModel.getAllSceneRequestCall(logView.getContext()).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                logView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                GetSceneListTask getSceneListTask = new GetSceneListTask(logModel, logView);
                getSceneListTask.execute(response);
            }
        });
    }

    @Override
    public void requestAllEquipment() {
        logView.startLoading();
        logModel.getAllEquipmentRequestCall(logView.getContext()).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                logView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                GetEquipmentListTask getEquipmentListTask = new GetEquipmentListTask(logModel, logView);
                getEquipmentListTask.execute(response);
            }
        });
    }

    @Override
    public void requestAppointedEquipment(String sceneId) {
        logView.startLoading();
        logModel.getAppointedEquipmentRequestCall(logView.getContext(), sceneId).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                logView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                GetEquipmentListTask getEquipmentListTask = new GetEquipmentListTask(logModel, logView);
                getEquipmentListTask.execute(response);
            }
        });
    }

    @Override
    public void startSearch(String startTime, String endTime, String sceneId, String equipmentId, String type, String page, String rows) {
        logView.startLoading();
        logModel.getSearchRequestCall(logView.getContext(), startTime, endTime, sceneId, equipmentId, type, page, rows).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                logView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                GetOperationLogListTask getOperationLogListTask = new GetOperationLogListTask(logModel, logView);
                getOperationLogListTask.setNotifyTag(logView.getNotifyTag());
                getOperationLogListTask.execute(response);
            }
        });
    }

    @Override
    public List<OperationLog> getOperationLog() {
        return logModel.getOperationLogList();
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {
        logModel.clearData();
    }

    @Override
    public Context getContext() {
        return logView.getContext();
    }
}
