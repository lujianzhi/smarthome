package com.smarthome.widget;

import android.content.Context;
import android.view.View;

import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.R;

/***
 * Created by Lawson on 2016/5/20.
 */
public class AddEquipmentDialog extends EditEquipmentDialog {

    private String sceneId;

    public AddEquipmentDialog(Context context,
                              IBasePresenter basePresenter,
                              String sceneId) {
        super(context, basePresenter, "", "", "", "");

        this.sceneId = sceneId;

    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                ((EquipmentMVPContract.IEquipmentPresenter) basePresenter).requestAddEquipment(
                        sceneId,
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
