package com.smarthome.MVPpresenter;

import android.content.Context;

import com.smarthome.AsyncTask.GetEquipmentListTask;
import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.entity.Equipment;
import com.smarthome.utils.LogUtils;
import com.smarthome.utils.MyJsonStrUtils;
import com.smarthome.utils.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/20.
 */
public class EquipmentPresenter implements EquipmentMVPContract.IEquipmentPresenter {
    private static final String TAG = EquipmentPresenter.class.getSimpleName();

    private EquipmentMVPContract.IEquipmentView equipmentView;
    private EquipmentMVPContract.IEquipmentModel equipmentModel;

    public EquipmentPresenter(EquipmentMVPContract.IEquipmentView equipmentView,
                              EquipmentMVPContract.IEquipmentModel equipmentModel) {
        this.equipmentView = equipmentView;
        this.equipmentModel = equipmentModel;
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {
        equipmentModel.clearData();
    }

    @Override
    public Context getContext() {
        return equipmentView.getContext();
    }

    @Override
    public void requestEquipmentList(String sceneId, String rows, String page) {
        equipmentView.startLoading();
        equipmentModel.getEquipmentListRequestCall(equipmentView.getContext(), sceneId, rows, page).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                equipmentView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                if (1 == MyJsonStrUtils.getCode(response)) {
                    GetEquipmentListTask getEquipmentListTask = new GetEquipmentListTask(equipmentModel, equipmentView);
                    getEquipmentListTask.execute(response);
                } else {
                    ToastUtils.showShortToast(MyJsonStrUtils.getMessage(response));
                }
            }
        });
    }

    @Override
    public List<Equipment> getEquipmentList() {
        return equipmentModel.getEquipmentList();
    }

    @Override
    public void changeStatus(final String equipmentId, final String state, final int position) {
        equipmentModel.changeStatus(equipmentView.getContext(), equipmentId, state).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                if (1 == MyJsonStrUtils.getCode(response)) {
                    equipmentModel.parseStatus(response, String.valueOf(position));
                    equipmentView.notifyDataChanged();

                }
            }
        });
    }

    @Override
    public void requestAddEquipment(String sceneId, String name, String equipmentComment, String isRemind) {
        equipmentModel.getAddEquipmentRequestCall(equipmentView.getContext(), sceneId, name, equipmentComment, isRemind).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                equipmentView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                equipmentView.executeOnResume();
                equipmentView.stopLoading();
            }
        });
    }
}
