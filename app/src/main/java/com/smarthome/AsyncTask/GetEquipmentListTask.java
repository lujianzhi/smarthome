package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.MVPContract.LogMVPContract;
import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.Equipment;
import com.smarthome.utils.LogUtils;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class GetEquipmentListTask extends AsyncTask<String, Void, List<Equipment>> {

    private IBaseModel baseModel;
    private IBaseView baseView;

    public GetEquipmentListTask(IBaseModel baseModel, IBaseView baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
    }

    @Override
    protected List<Equipment> doInBackground(String... params) {
        LogUtils.i("解析设备列表的线程 : " + Thread.currentThread().getName());
        if (baseModel instanceof EquipmentMVPContract.IEquipmentModel) {
            ((EquipmentMVPContract.IEquipmentModel) baseModel).parseEquipmentList(params[0]);
            return ((EquipmentMVPContract.IEquipmentModel) baseModel).getEquipmentList();
        } else {
            ((LogMVPContract.ILogModel) baseModel).parseEquipmentList(params[0]);
            return ((LogMVPContract.ILogModel) baseModel).getEquipmentList();
        }
    }

    @Override
    protected void onPostExecute(List<Equipment> equipments) {
        if (baseView instanceof EquipmentMVPContract.IEquipmentView) {
            baseView.notifyDataChanged();
        } else {
            ((LogMVPContract.ILogView) baseView).setEquipmentList(equipments);
        }
        baseView.stopLoading();
    }
}
