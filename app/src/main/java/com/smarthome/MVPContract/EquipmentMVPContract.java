package com.smarthome.MVPContract;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.Equipment;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public interface EquipmentMVPContract {

    interface IEquipmentView extends IBaseView {

        /**
         * 执行onResume方法
         */
        void executeOnResume();
    }

    interface IEquipmentPresenter extends IBasePresenter {

        /**
         * 请求设备列表
         */
        void requestEquipmentList(String sceneId, String rows, String page);

        /**
         * 获取设备列表
         */
        List<Equipment> getEquipmentList();

        /**
         * 修改状态
         */
        void changeStatus(String equipmentId, String state, int position);

        /**
         * 添加设备
         */
        void requestAddEquipment(String sceneId, String name, String equipmentComment, String isRemind);
    }

    interface IEquipmentModel extends IBaseModel {

        /**
         * 请求设备列表
         */
        RequestCall getEquipmentListRequestCall(String sceneId, String rows, String page);

        /**
         * 解析设备列表
         */
        void parseEquipmentList(String jsonStr);

        /**
         * 获取设备列表
         */
        List<Equipment> getEquipmentList();

        /**
         * 修改状态
         */
        RequestCall changeStatus(String equipmentId, String state);

        /**
         * 解析状态
         */
        void parseStatus(String jsonStr, String position);

        /**
         * 获取添加设备的RequestCall
         */
        RequestCall getAddEquipmentRequestCall(String sceneId, String name, String equipmentComment, String isRemind);
    }

}
