package com.smarthome.MVPContract;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.OperationLog;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

/***
 * Created by Lawson on 2016/5/21.
 */
public interface EquipmentDetailMVPContract {

    interface IEquipmentDetailView extends IBaseView {

        void updateStatus(String status);
    }

    interface IEquipmentDetailPresenter extends IBasePresenter {

        /**
         * 请求设备详情数据列表
         */
        void requestEquipmentDetailList(String sceneId, String eqId, String type, String page, String rows);

        /**
         * 获取设备详情列表
         */
        List<OperationLog> getOperationLogList();

        /**
         * 修改状态
         */
        void changeStatus(String equipmentId, String state);
    }

    interface IEquipmentDetailModel extends IBaseModel {

        /**
         * 返回设备详情列表的RequestCall
         */
        RequestCall getEquipmentDetailRequestCall(String sceneId, String eqId, String type, String page, String rows);

        /**
         * 解析设备详情列表
         */
        void parserEquipmentDetailList(String jsonStr);

        /**
         * 获取设备详情列表
         */
        List<OperationLog> getOperationLogList();

        /**
         * 修改状态
         */
        RequestCall changeStatus(String equipmentId, String state);

        /**
         * 解析状态
         */
        String parseStatus(String jsonStr);
    }

}
