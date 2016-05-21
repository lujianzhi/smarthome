package com.smarthome.MVPpresenter;

import com.smarthome.AsyncTask.GetOperationLogListTask;
import com.smarthome.AsyncTask.StatusChangeTask;
import com.smarthome.MVPContract.EquipmentDetailMVPContract;
import com.smarthome.entity.OperationLog;
import com.smarthome.utils.LogUtils;
import com.smarthome.utils.MyJsonStrUtils;
import com.smarthome.utils.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/21.
 */
public class EquipmenDetailPresenter implements EquipmentDetailMVPContract.IEquipmentDetailPresenter {
    private static final String TAG = EquipmenDetailPresenter.class.getSimpleName();

    private EquipmentDetailMVPContract.IEquipmentDetailView equipmentDetailView;
    private EquipmentDetailMVPContract.IEquipmentDetailModel equipmentDetailModel;

    public EquipmenDetailPresenter(EquipmentDetailMVPContract.IEquipmentDetailView equipmentDetailView,
                                   EquipmentDetailMVPContract.IEquipmentDetailModel equipmentDetailModel) {
        this.equipmentDetailView = equipmentDetailView;
        this.equipmentDetailModel = equipmentDetailModel;
    }

    @Override
    public void initData() {
    }

    @Override
    public void clearData() {
        equipmentDetailModel.clearData();
    }

    @Override
    public void requestEquipmentDetailList(String sceneId, String eqId, String type, String page, String rows) {
        equipmentDetailModel.getEquipmentDetailRequestCall(sceneId, eqId, type, page, rows)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        LogUtils.e(TAG, e.getMessage());
                        equipmentDetailView.stopLoading();
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtils.i(TAG, response);
                        if (1 == MyJsonStrUtils.getCode(response)) {
                            GetOperationLogListTask getEquipmentListTask = new GetOperationLogListTask(equipmentDetailModel, equipmentDetailView);
                            getEquipmentListTask.execute(response);
                        } else {
                            ToastUtils.showShortToast(MyJsonStrUtils.getMessage(response));
                        }

                    }
                });
    }

    @Override
    public List<OperationLog> getOperationLogList() {
        return equipmentDetailModel.getOperationLogList();
    }

    @Override
    public void changeStatus(String equipmentId, String state) {
        equipmentDetailModel.changeStatus(equipmentId, state).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                StatusChangeTask statusChangeTask = new StatusChangeTask(equipmentDetailView, equipmentDetailModel);
                statusChangeTask.execute(response);
            }
        });
    }
}
