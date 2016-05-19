package com.smarthome.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.R;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class LogActivity extends BaseActivity {
    @BindView(R.id.start_time)
    ButtonRectangle startTime;
    @BindView(R.id.end_time)
    ButtonRectangle endTime;
    @BindView(R.id.scene)
    AutoCompleteTextView scene;
    @BindView(R.id.equipment)
    AutoCompleteTextView equipment;
    @BindView(R.id.log_recyclerView)
    RecyclerView logRecyclerView;

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_log;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.log;
    }

    @Override
    protected void initPresenterAndModel() {

    }

    @Override
    public void onClick(View v) {

    }
}
