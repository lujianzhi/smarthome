package com.smarthome.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.MVPContract.EquipmentDetailMVPContract;
import com.smarthome.R;

/***
 * Created by Lawson on 2016/5/20.
 */
public class EditEquipmentDialog extends Dialog implements View.OnClickListener {

    private View mainView;

    private EditText equipment_name;
    private EditText equipment_comment;
    private RadioButton equipment_status_on;
    private RadioButton equipment_status_off;

    private String status = "0";
    private String equipmentId;

    private EquipmentDetailMVPContract.IEquipmentDetailPresenter equipmentDetailPresenter;

    public EditEquipmentDialog(Context context,
                               EquipmentDetailMVPContract.IEquipmentDetailPresenter equipmentDetailPresenter,
                               String equipmentId,
                               String equipmentName,
                               String equipmentComment,
                               String equipmentStatus) {
        super(context, R.style.emptyDialog);
        this.equipmentDetailPresenter = equipmentDetailPresenter;
        this.equipmentId = equipmentId;
        mainView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_equipment, null);
        setContentView(mainView);
        initViews();
        equipment_name.setText(equipmentName);
        equipment_comment.setText(equipmentComment);
        if ("1".equals(equipmentStatus)) {
            equipment_status_on.setChecked(true);
        } else {
            equipment_status_off.setChecked(true);
        }
    }

    private void initViews() {
        equipment_name = (EditText) mainView.findViewById(R.id.equipment_name);
        equipment_comment = (EditText) mainView.findViewById(R.id.equipment_comment);
        RadioGroup equipment_status = (RadioGroup) mainView.findViewById(R.id.equipment_status);
        equipment_status_on = (RadioButton) mainView.findViewById(R.id.equipment_status_on);
        equipment_status_off = (RadioButton) mainView.findViewById(R.id.equipment_status_off);
        ButtonRectangle save = (ButtonRectangle) mainView.findViewById(R.id.save);
        ButtonRectangle back = (ButtonRectangle) mainView.findViewById(R.id.back);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
        equipment_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.equipment_status_on:
                        status = "1";
                        break;
                    case R.id.equipment_status_off:
                        status = "0";
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                equipmentDetailPresenter.editEquipmentInfo(equipmentId,
                        equipment_name.getText().toString(),
                        equipment_comment.getText().toString(),
                        status);
                break;
            case R.id.back:
                dismiss();
                break;
        }
    }
}
