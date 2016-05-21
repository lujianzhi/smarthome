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
    }

}
