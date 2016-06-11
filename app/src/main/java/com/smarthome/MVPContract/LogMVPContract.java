package com.smarthome.MVPContract;

import android.content.Context;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.Equipment;
import com.smarthome.entity.OperationLog;
import com.smarthome.entity.Scene;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

/***
 * Created by Lawson on 2016/5/21.
 */
public interface LogMVPContract {

    interface ILogView extends IBaseView {

        /**
         * 设置场景列表
         */
        void setSceneList(List<Scene> sceneList);

        /**
         * 设置设备列表
         */
        void setEquipmentList(List<Equipment> equipmentList);

        /**
         * 设置设备列表
         */
        void setOperationLogList(List<OperationLog> operationLogList);

        /**
         * 获取日志类型
         */
        int getNotifyTag();
    }

    interface ILogPresenter extends IBasePresenter {

        /**
         * 获取全部场景
         */
        void requestAllScene();

        /**
         * 获取全部设备
         */
        void requestAllEquipment();

        /**
         * 获取相应场景的设备
         */
        void requestAppointedEquipment(String sceneId);

        /**
         * 搜索
         */
        void startSearch(String startTime, String endTime, String sceneId, String equipmentId, String type, String page, String rows);

        /**
         * 获取搜索内容
         */
        List<OperationLog> getOperationLog();

    }

    interface ILogModel extends IBaseModel {

        /**
         * 获取全部场景的RequestCall
         */
        RequestCall getAllSceneRequestCall(Context context);

        /**
         * 解析全部场景
         */
        void parserAllScene(String jsonStr);

        /**
         * 获取全部场景
         */
        List<Scene> getAllScene();

        /**
         * 获取全部设备的RequestCall
         */
        RequestCall getAllEquipmentRequestCall(Context context);

        /**
         * 解析设备列表
         */
        void parseEquipmentList(String jsonStr);

        /**
         * 获取设备列表
         */
        List<Equipment> getEquipmentList();

        /**
         * 获取相应场景的设备
         */
        RequestCall getAppointedEquipmentRequestCall(Context context, String sceneId);

        /**
         * 获取搜索结果
         */
        RequestCall getSearchRequestCall(Context context, String startTime, String endTime, String sceneId, String equipmentId, String type, String page, String rows);

        /**
         * 解析搜索结果
         */
        void parseOperationLogList(String jsonStr);

        /**
         * 获取搜索结果列表
         */
        List<OperationLog> getOperationLogList();

    }

}
