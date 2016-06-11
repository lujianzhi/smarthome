package com.smarthome.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.utils.ToastUtils;

/***
 * Created by Lawson on 2016/6/10.
 */
public class TemperatureReceiver extends BroadcastReceiver {

    private MainMVPContract.IMainPresenter mainPresenter;

    public TemperatureReceiver(MainMVPContract.IMainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            mainPresenter.requestTemperature();
        } else {
            ToastUtils.showShortToast("接受温度接口失败-广播");
        }
    }
}
