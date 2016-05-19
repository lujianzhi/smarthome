package com.smarthome.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.R;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class EquipmentDetail extends BaseActivity {
    @BindView(R.id.equipment)
    TextView equipment;
    @BindView(R.id.status)
    ButtonRectangle status;
    @BindView(R.id.equipment_recyclerView)
    RecyclerView equipmentRecyclerView;

    @Override
    protected void initViews() {

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

    }

    @Override
    public void onClick(View v) {

    }
}
