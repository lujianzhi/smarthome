package com.smarthome.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.MVPmodel.EquipmentModel;
import com.smarthome.MVPpresenter.EquipmentPresenter;
import com.smarthome.R;
import com.smarthome.adapter.EquipmentAdapter;
import com.smarthome.widget.AddEquipmentDialog;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class EquipmentActivity extends BaseActivity implements EquipmentMVPContract.IEquipmentView {
    @BindView(R.id.equipment_recyclerView)
    RecyclerView equipmentRecyclerView;
    @BindView(R.id.scene_name)
    TextView sceneNameTV;
    @BindView(R.id.add)
    ImageView add;

    private EquipmentAdapter equipmentAdapter;

    private EquipmentMVPContract.IEquipmentPresenter equipmentPresenter;
    private String sceneId;
    private String sceneName;

    private AddEquipmentDialog addEquipmentDialog;

    @Override
    protected void onResume() {
        super.onResume();
        initIntentData();
        equipmentPresenter.clearData();
        equipmentPresenter.requestEquipmentList(sceneId, "20", "1");
    }

    @Override
    protected void initIntentData() {
        sceneId = getIntent().getStringExtra(SceneActivity.SCENE_ID);
        sceneName = getIntent().getStringExtra(SceneActivity.SCENE_NAME);
    }

    @Override
    protected void initViews() {
        sceneNameTV.setText(sceneName);
        equipmentAdapter = new EquipmentAdapter(this, equipmentPresenter);
        equipmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        equipmentRecyclerView.setAdapter(equipmentAdapter);

        add.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_equipment;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.equipment;
    }

    @Override
    protected void initPresenterAndModel() {
        equipmentPresenter = new EquipmentPresenter(this, new EquipmentModel());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                showAddEquipmentDialog();
                break;
        }
    }

    private void showAddEquipmentDialog() {
        addEquipmentDialog = new AddEquipmentDialog(this, equipmentPresenter, sceneId);
        addEquipmentDialog.setCancelable(false);
        addEquipmentDialog.setCanceledOnTouchOutside(false);
        addEquipmentDialog.show();
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
        equipmentAdapter.setEquipmentList(equipmentPresenter.getEquipmentList());
        equipmentAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void executeOnResume() {
        addEquipmentDialog.dismiss();
        onResume();
    }
}
