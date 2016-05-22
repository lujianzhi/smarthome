package com.smarthome.MVPContract;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.cammonitor.config.CamMonitorParameter;

/***
 * Created by Lawson on 2016/5/22.
 */
public interface CamMonitorMVPContract {

    interface ICamMonitorView extends IBaseView {

        /**
         * 为CamMonitorView填充数据
         */
        void fillCamMonitorViewData(CamMonitorParameter camMonitorParameter);

    }

    interface ICamMonitorPresenter extends IBasePresenter {

        /**
         * 请求视频监控数据
         */
        void requestCamMonitor();
    }

    interface ICamMonitorModel extends IBaseModel {

        /**
         * 提供视频监控请求
         */
        CamMonitorParameter requestCamMonitor();
    }

}
