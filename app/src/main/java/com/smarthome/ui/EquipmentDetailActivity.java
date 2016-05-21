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
import com.smarthome.widget.EditEquipmentDialog;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class EquipmentDetailActivity extends BaseActivity implements EquipmentDetailMVPContract.IEquipmentDetailView {
    public static final String EQUIPMENT_NAME = "equipment_name";
    public static final String EQUIPMENT_ID = "equipment_id";
    public static final String EQUIPMENT_STATUS = "equipment_status";
    public static final String EQUIPMENT_COMMENT = "equipment_comment";
    public static final String SCENE_ID = "scene_id";

    @BindView(R.id.equipment)
    TextView equipment;
    @BindView(R.id.equipment_recyclerView)
    RecyclerView equipmentRecyclerView;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.edit)
    TextView edit;

    private String equipmentName;
    private String equipmentId;
    private String equipmentComment;
    private String equipmentStatus;
    private String sceneId;

    private EditEquipmentDialog editDialog;

    private EquipmentDetailMVPContract.IEquipmentDetailPresenter equipmentDetailPresenter;

    private OperationLogAdapter operationLogAdapter;

    @Override
    protected void initIntentData() {
        Bundle data = getIntent().getExtras();
        equipmentName = data.getString(EquipmentDetailActivity.EQUIPMENT_NAME, "");
        equipmentId = data.getString(EquipmentDetailActivity.EQUIPMENT_ID, "");
        equipmentComment = data.getString(EquipmentDetailActivity.EQUIPMENT_COMMENT, "");
        equipmentStatus = data.getString(EquipmentDetailActivity.EQUIPMENT_STATUS, "0");
        sceneId = data.getString(EquipmentDetailActivity.SCENE_ID);
    }

    @Override
    protected void initViews() {
        equipment.setText(equipmentName);
        comment.setText(equipmentComment);
        edit.setOnClickListener(this);

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
        switch (v.getId()) {
            case R.id.edit:
                showEditDialog();
                break;
        }
    }

    private void showEditDialog() {
        editDialog = new EditEquipmentDialog(
                this,
                equipmentDetailPresenter,
                equipmentId,
                equipmentName,
                equipmentComment,
                equipmentStatus);
        editDialog.setCancelable(false);
        editDialog.setCanceledOnTouchOutside(false);
        editDialog.show();
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
    public void notifyTitle(String equipmentName, String equipmentComment) {
        editDialog.dismiss();
        equipment.setText(equipmentName);
        comment.setText(equipmentComment);
    }
}
