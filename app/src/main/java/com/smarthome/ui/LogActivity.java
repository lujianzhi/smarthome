package com.smarthome.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.MVPContract.LogMVPContract;
import com.smarthome.MVPmodel.LogModel;
import com.smarthome.MVPpresenter.LogPresenter;
import com.smarthome.R;
import com.smarthome.adapter.EquipmentSpinnerAdapter;
import com.smarthome.adapter.OperationLogAdapter;
import com.smarthome.adapter.SceneSpinnerAdapter;
import com.smarthome.entity.Equipment;
import com.smarthome.entity.OperationLog;
import com.smarthome.entity.Scene;
import com.smarthome.utils.ToastUtils;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class LogActivity extends BaseActivity implements LogMVPContract.ILogView {

    @BindView(R.id.start_time)
    Button startTime;
    @BindView(R.id.end_time)
    Button endTime;
    @BindView(R.id.scene)
    Spinner scene;
    @BindView(R.id.equipment)
    Spinner equipment;
    @BindView(R.id.search)
    ButtonRectangle search;
    @BindView(R.id.log_recyclerView)
    RecyclerView logRecyclerView;

    protected LogMVPContract.ILogPresenter logPresenter;

    protected OperationLogAdapter operationLogAdapter;
    protected SceneSpinnerAdapter sceneSpinnerAdapter;
    protected EquipmentSpinnerAdapter equipmentSpinnerAdapter;

    protected String sceneId = "";
    protected String equipmentId = "";
    protected boolean isSceneFirst = true;
    protected boolean isEquipmentFirst = true;

    @Override
    protected void initViews() {

        logPresenter.requestAllScene();
        logPresenter.requestAllEquipment();

        sceneSpinnerAdapter = new SceneSpinnerAdapter(this);
        scene.setAdapter(sceneSpinnerAdapter);
        scene.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isSceneFirst) {
                    Scene scene = (Scene) parent.getItemAtPosition(position);
                    sceneId = String.valueOf(scene.getId());
                    logPresenter.requestAppointedEquipment(sceneId);
                }
                isSceneFirst = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                isSceneFirst = false;
            }
        });

        equipmentSpinnerAdapter = new EquipmentSpinnerAdapter(this);
        equipment.setAdapter(equipmentSpinnerAdapter);
        equipment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isEquipmentFirst) {
                    Equipment equipment = (Equipment) parent.getItemAtPosition(position);
                    equipmentId = String.valueOf(equipment.getId());
                }
                isEquipmentFirst = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                isSceneFirst = false;
            }
        });

        operationLogAdapter = new OperationLogAdapter();
        operationLogAdapter.setHorizontal(true);
        logRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        logRecyclerView.setAdapter(operationLogAdapter);

        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        search.setOnClickListener(this);
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
        logPresenter = new LogPresenter(this, new LogModel());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_time:
                showDataPickerDialog(startTime);
                break;
            case R.id.end_time:
                showDataPickerDialog(endTime);
                break;
            case R.id.search:
                startSearch(startTime.getText().toString(),
                        endTime.getText().toString(),
                        sceneId,
                        equipmentId,
                        String.valueOf(getNotifyTag()),
                        "1",
                        "20");
                break;
        }
    }

    protected void startSearch(String startTime, String endTime, String sceneId, String equipmentId, String type, String page, String rows) {
        logPresenter.startSearch(startTime, endTime, sceneId, equipmentId, type, page, rows);
    }

    protected void showDataPickerDialog(final Button button) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String myYear = String.valueOf(year);
                String myMonth = String.valueOf((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1));
                String myDay = String.valueOf((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                String myDate = myYear + "-" + myMonth + "-" + myDay;
                button.setText(myDate);
                judgeDate(startTime.getText().toString(), endTime.getText().toString());
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    protected void judgeDate(String startTimeDate, String endTimeDate) {
        int startInt = Integer.parseInt(startTimeDate.replace("-", ""));
        int endInt = Integer.parseInt(endTimeDate.replace("-", ""));
        if (startInt > endInt) {
            ToastUtils.showShortToast(R.string.judge_time);
            endTime.setText(startTimeDate);
        }
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
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setSceneList(List<Scene> sceneList) {
        sceneSpinnerAdapter.setList(sceneList);
        sceneSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEquipmentList(List<Equipment> equipmentList) {
        equipmentSpinnerAdapter.setList(equipmentList);
        equipmentSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOperationLogList(List<OperationLog> operationLogList) {
        operationLogAdapter.setOperationLogList(operationLogList);
        operationLogAdapter.notifyDataSetChanged();
    }

    @Override
    public int getNotifyTag() {
        return 0;
    }
}
