package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.EquipmentDetailMVPContract;
import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPview.IBaseView;

/***
 * Created by Lawson on 2016/5/20.
 */
public class StatusChangeTask extends AsyncTask<String, Void, String> {

    private IBaseView baseView;
    private IBaseModel baseModel;

    public StatusChangeTask(IBaseView baseView, IBaseModel baseModel) {
        this.baseView = baseView;
        this.baseModel = baseModel;
    }

    @Override
    protected String doInBackground(String... params) {
        if (baseModel instanceof EquipmentMVPContract.IEquipmentModel) {
            ((EquipmentMVPContract.IEquipmentModel) baseModel).parseStatus(params[0], params[1]);
            return null;
        } else {
            return ((EquipmentDetailMVPContract.IEquipmentDetailModel) baseModel).parseStatus(params[0]);
        }
    }

    @Override
    protected void onPostExecute(String status) {
        if (baseView instanceof EquipmentDetailMVPContract.IEquipmentDetailView) {
            ((EquipmentDetailMVPContract.IEquipmentDetailView) baseView).updateStatus(status);
        }
        baseView.notifyDataChanged();
        baseView.stopLoading();
    }
}
