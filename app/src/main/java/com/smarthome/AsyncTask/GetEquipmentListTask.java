package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.entity.Equipment;
import com.smarthome.utils.LogUtils;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class GetEquipmentListTask extends AsyncTask<String, Void, List<Equipment>> {

    private EquipmentMVPContract.IEquipmentModel equipmentModel;
    private EquipmentMVPContract.IEquipmentView equipmentView;

    public GetEquipmentListTask(EquipmentMVPContract.IEquipmentModel equipmentModel,
                                EquipmentMVPContract.IEquipmentView equipmentView) {
        this.equipmentModel = equipmentModel;
        this.equipmentView = equipmentView;
    }

    @Override
    protected List<Equipment> doInBackground(String... params) {
        LogUtils.i("解析设备列表的线程 : " + Thread.currentThread().getName());
        equipmentModel.parseEquipmentList(params[0]);
        return equipmentModel.getEquipmentList();
    }

    @Override
    protected void onPostExecute(List<Equipment> equipments) {
        equipmentView.notifyDataChanged();
        equipmentView.stopLoading();
    }
}
