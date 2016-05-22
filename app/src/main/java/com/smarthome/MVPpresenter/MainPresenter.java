package com.smarthome.MVPpresenter;

import com.smarthome.AsyncTask.GetOperationLogListTask;
import com.smarthome.AsyncTask.GetSceneListTask;
import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.utils.LogUtils;
import com.smarthome.utils.MyJsonStrUtils;
import com.smarthome.utils.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/20.
 */
public class MainPresenter implements MainMVPContract.IMainPresenter {
    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainMVPContract.IMainModel mainModel;
    private MainMVPContract.IMainView mainView;

    public MainPresenter(MainMVPContract.IMainModel mainModel,
                         MainMVPContract.IMainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void requestSceneList() {
        mainView.startLoading();
        mainModel.getSceneRequestCall().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                mainView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                if (1 == mainModel.parseJsonCode(response)) {
                    GetSceneListTask getSceneListTask = new GetSceneListTask(mainModel, mainView);
                    getSceneListTask.execute(response);
                }
                mainView.stopLoading();
            }
        });
    }

    @Override
    public void requestWarnInfoList(String type,String page,String rows) {
        mainModel.getWarnInfoRequestCall(type, page, rows).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                mainView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                if (1 == mainModel.parseJsonCode(response)) {
                    GetOperationLogListTask getOperationLogListTask = new GetOperationLogListTask(mainModel, mainView);
                    getOperationLogListTask.setNotifyTag(0);
                    getOperationLogListTask.execute(response);
                }
                mainView.stopLoading();
            }
        });
    }

    @Override
    public void requestLogMessageList(String type,String page,String rows) {
        mainModel.getLogMessageRequestCall(type, page, rows).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                mainView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                if (1 == mainModel.parseJsonCode(response)) {
                    GetOperationLogListTask getOperationLogListTask = new GetOperationLogListTask(mainModel, mainView);
                    getOperationLogListTask.setNotifyTag(1);
                    getOperationLogListTask.execute(response);
                } else {
                    ToastUtils.showShortToast(MyJsonStrUtils.getMessage(response));
                }
            }
        });
    }

    @Override
    public void requestTemperature() {
        mainModel.getTemperatureRequestCall().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                mainView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                if (1 == mainModel.parseJsonCode(response)) {
                    mainView.setTemperature(MyJsonStrUtils.getMessage(response));
                } else {
                    ToastUtils.showShortToast(MyJsonStrUtils.getMessage(response));
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {
        mainModel.clearData();
    }

}
