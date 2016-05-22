package com.smarthome.MVPmodel;

import android.os.Environment;

import com.smarthome.MVPContract.CamMonitorMVPContract;
import com.smarthome.MyApplication;
import com.smarthome.cammonitor.config.CamMonitorParameter;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.User;

/***
 * Created by Lawson on 2016/5/22.
 */
public class CamMonitorModel implements CamMonitorMVPContract.ICamMonitorModel {
    @Override
    public int parseJsonCode(String jsonStr) {
        return 0;
    }

    @Override
    public String parseJsonMessage(String jsonStr) {
        return null;
    }

    @Override
    public void clearData() {

    }

    @Override
    public CamMonitorParameter requestCamMonitor() {
        User user = MyApplication.getCurrentUser();
        CamMonitorParameter camMonitorParameter = new CamMonitorParameter();
        camMonitorParameter.setId(1);
        camMonitorParameter.setName(NetConfig.IP);
        camMonitorParameter.setIp(NetConfig.IP);
        camMonitorParameter.setPort(NetConfig.CAM_PORT);
        camMonitorParameter.setUsername(user.getUserName());
        camMonitorParameter.setPassword(user.getPassword());
        camMonitorParameter.setLocal_dir(Environment.getExternalStorageDirectory().getPath());
        return camMonitorParameter;
    }
}
