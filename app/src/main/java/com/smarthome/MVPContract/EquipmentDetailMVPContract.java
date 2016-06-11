package com.smarthome.MVPContract;

import android.content.Context;

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

        /**
         * 修改设备名和设备备注
         */
        void notifyTitle(String equipmentName, String equipmentComment);
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
         * 修改设备信息
         */
        void editEquipmentInfo(String equipmentId, String equipmentName, String equipmentComment, String status);

    }

    interface IEquipmentDetailModel extends IBaseModel {

        /**
         * 返回设备详情列表的RequestCall
         */
        RequestCall getEquipmentDetailRequestCall(Context context, String sceneId, String eqId, String type, String page, String rows);

        /**
         * 解析设备详情列表
         */
        void parserEquipmentDetailList(String jsonStr);

        /**
         * 获取设备详情列表
         */
        List<OperationLog> getOperationLogList();

        /**
         * 修改设备信息
         */
        RequestCall getEditEquipmentInfoRequestCall(Context context,String equipmentId, String equipmentName, String equipmentComment, String status);
    }

}
