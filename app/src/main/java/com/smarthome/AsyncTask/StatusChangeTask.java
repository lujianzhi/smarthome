package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.EquipmentMVPContract;

/***
 * Created by Lawson on 2016/5/20.
 */
public class StatusChangeTask extends AsyncTask<String, Void, String> {

    private EquipmentMVPContract.IEquipmentView equipmentView;
    private EquipmentMVPContract.IEquipmentModel equipmentModel;

    public StatusChangeTask(EquipmentMVPContract.IEquipmentView equipmentView, EquipmentMVPContract.IEquipmentModel equipmentModel) {
        this.equipmentView = equipmentView;
        this.equipmentModel = equipmentModel;
    }

    @Override
    protected String doInBackground(String... params) {
        equipmentModel.parseStatus(params[0], params[1]);
        return null;
    }

    @Override
    protected void onPostExecute(String status) {
        equipmentView.notifyDataChanged();
        equipmentView.stopLoading();
    }
}
