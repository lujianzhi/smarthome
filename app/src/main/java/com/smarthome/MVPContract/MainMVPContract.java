package com.smarthome.MVPContract;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.OperationLog;
import com.smarthome.entity.Scene;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public interface MainMVPContract {

    interface IMainView extends IBaseView {

        /**
         * 设置场景列表
         */
        void setSceneList(List<Scene> sceneList);

        /**
         * 设置警告信息列表
         */
        void setWarnInfoList(List<OperationLog> warnInfoList);

        /**
         * 设置警告信息列表
         */
        void setLogInfoList(List<OperationLog> logInfoList);

        /**
         * 设置室内气温
         */
        void setTemperature(String temperatureStr);

    }

    interface IMainPresenter extends IBasePresenter {

        /**
         * 请求场景列表
         */
        void requestSceneList();

        /**
         * 请求警告信息列表
         */
        void requestWarnInfoList(String type, String page, String rows);

        /**
         * 请求日志消息列表
         */
        void requestLogMessageList(String type, String page, String rows);

        /**
         * 请求气温
         */
        void requestTemperature();
    }

    interface IMainModel extends IBaseModel {

        /**
         * 获取场景RequestCall
         */
        RequestCall getSceneRequestCall();

        /**
         * 获取警告信息RequestCall
         */
        RequestCall getWarnInfoRequestCall(String type, String page, String rows);

        /**
         * 获取日志消息RequestCall
         */
        RequestCall getLogMessageRequestCall(String type, String page, String rows);

        /**
         * 获取气温RequestCall
         */
        RequestCall getTemperatureRequestCall();

        /**
         * 解析场景列表
         */
        void parseSceneList(String jsonStr);

        /**
         * 解析警告信息列表
         */
        void parseWarnInfoList(String jsonStr);

        /**
         * 解析日志消息列表
         */
        void parseLogMessageList(String jsonStr);

        /**
         * 获取场景列表
         */
        List<Scene> getSceneList();

        /**
         * 获取警告信息列表
         */
        List<OperationLog> getWarnInfoList();

        /**
         * 获取日志消息列表
         */
        List<OperationLog> getLogMessageList();

        /**
         * 清除日志消息
         */
        void clearLogMessageList();

        /**
         * 清除警告消息
         */
        void clearWarmInfoList();

    }

}
