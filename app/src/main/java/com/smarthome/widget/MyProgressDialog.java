package com.smarthome.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.smarthome.R;

/***
 * Created by Lawson on 2016/4/24.
 */
public class MyProgressDialog extends Dialog {

    private View popView;

    public MyProgressDialog(Context context) {
        super(context, R.style.emptyDialog);
        popView = View.inflate(context, R.layout.progress_dialog_layout, null);
        setContentView(popView);
    }

}
