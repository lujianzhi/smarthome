package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.EquipmentDetailMVPContract;
import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.OperationLog;
import com.smarthome.utils.LogUtils;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class GetOperationLogListTask extends AsyncTask<String, Void, List<OperationLog>> {

    private IBaseModel baseModel;
    private IBaseView baseView;
    private int notifyTag;

    public GetOperationLogListTask(IBaseModel baseModel, IBaseView baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
    }

    @Override
    protected List<OperationLog> doInBackground(String... params) {
        if (notifyTag == 1) {
            LogUtils.i("解析警告信息列表的线程 : " + Thread.currentThread().getName());
        } else if (notifyTag == 0) {
            LogUtils.i("解析日志消息列表的线程 : " + Thread.currentThread().getName());
        }
        if (baseModel instanceof MainMVPContract.IMainModel) {
            ((MainMVPContract.IMainModel) baseModel).parseWarnInfoList(params[0]);
            return ((MainMVPContract.IMainModel) baseModel).getWarnInfoList();
        } else {
            ((EquipmentDetailMVPContract.IEquipmentDetailModel) baseModel).parserEquipmentDetailList(params[0]);
            return ((EquipmentDetailMVPContract.IEquipmentDetailModel) baseModel).getOperationLogList();
        }
    }

    public void setNotifyTag(int notifyTag) {
        this.notifyTag = notifyTag;
    }

    @Override
    protected void onPostExecute(List<OperationLog> operationLogs) {
        if (baseView instanceof MainMVPContract.IMainView) {
            if (notifyTag == 1) {
                ((MainMVPContract.IMainView) baseView).setWarnInfoList(operationLogs);
            } else if (notifyTag == 0) {
                ((MainMVPContract.IMainView) baseView).setLogInfoList(operationLogs);
            }
        } else {
            baseView.notifyDataChanged();
        }
        baseView.stopLoading();
        this.cancel(false);
    }
}
