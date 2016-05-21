package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.entity.OperationLog;
import com.smarthome.utils.LogUtils;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class GetOperationLogListTask extends AsyncTask<String, Void, List<OperationLog>> {

    private MainMVPContract.IMainModel mainModel;
    private MainMVPContract.IMainView mainView;
    private int notifyTag;

    public GetOperationLogListTask(MainMVPContract.IMainModel mainModel, MainMVPContract.IMainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    protected List<OperationLog> doInBackground(String... params) {
        mainModel.parseWarnInfoList(params[0]);
        if (notifyTag == 1) {
            LogUtils.i("解析警告信息列表的线程 : " + Thread.currentThread().getName());
        } else if (notifyTag == 0) {
            LogUtils.i("解析日志消息列表的线程 : " + Thread.currentThread().getName());
        }
        return mainModel.getWarnInfoList();
    }

    public void setNotifyTag(int notifyTag) {
        this.notifyTag = notifyTag;
    }

    @Override
    protected void onPostExecute(List<OperationLog> operationLogs) {
        if (notifyTag == 1) {
            mainView.setWarnInfoList(operationLogs);
        } else if (notifyTag == 0) {
            mainView.setLogInfoList(operationLogs);
        }
    }
}
