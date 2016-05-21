package com.smarthome.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smarthome.MVPContract.EquipmentDetailMVPContract;
import com.smarthome.MVPmodel.EquipmentDetailModel;
import com.smarthome.MVPpresenter.EquipmentDetailPresenter;
import com.smarthome.R;
import com.smarthome.adapter.OperationLogAdapter;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class EquipmentDetailActivity extends BaseActivity implements EquipmentDetailMVPContract.IEquipmentDetailView {
    public static final String EQUIPMENT_NAME = "equipment_name";
    public static final String EQUIPMENT_ID = "equipment_id";
    public static final String EQUIPMENT_STATUS = "equipment_status";
    public static final String SCENE_ID = "scene_id";

    @BindView(R.id.equipment)
    TextView equipment;
    @BindView(R.id.equipment_recyclerView)
    RecyclerView equipmentRecyclerView;

    private String equipmentName;
    private String equipmentId;
    private String sceneId;

    private EquipmentDetailMVPContract.IEquipmentDetailPresenter equipmentDetailPresenter;

    private OperationLogAdapter operationLogAdapter;

    @Override
    protected void initIntentData() {
        Bundle data = getIntent().getExtras();
        equipmentName = data.getString(EquipmentDetailActivity.EQUIPMENT_NAME);
        equipmentId = data.getString(EquipmentDetailActivity.EQUIPMENT_ID);
        sceneId = data.getString(EquipmentDetailActivity.SCENE_ID);
    }

    @Override
    protected void initViews() {
        equipment.setText(equipmentName);

        equipmentDetailPresenter.requestEquipmentDetailList(sceneId, equipmentId, "", "1", "20");
        operationLogAdapter = new OperationLogAdapter();
        operationLogAdapter.setHorizontal(true);
        equipmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        equipmentRecyclerView.setAdapter(operationLogAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_equipment_detail;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.equipment_detail;
    }

    @Override
    protected void initPresenterAndModel() {
        equipmentDetailPresenter = new EquipmentDetailPresenter(this, new EquipmentDetailModel());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void startLoading() {
        showProgressDialog();
    }

    @Override
    public void stopLoading() {
        disMissProgressDialog();
    }

    @Override
    public void notifyDataChanged() {
        operationLogAdapter.setOperationLogList(equipmentDetailPresenter.getOperationLogList());
        operationLogAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateStatus(String status) {
    }
}
