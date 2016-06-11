package com.smarthome.MVPContract;

import android.content.Context;

import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPpresenter.IBasePresenter;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.Scene;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public interface SceneMVPContract {

    interface ISceneView extends IBaseView {

        /**
         * 设置场景列表
         */
        void setSceneList(List<Scene> sceneList);

        /**
         * 执行onResume方法
         */
        void executeOnResume();

    }

    interface IScenePresenter extends IBasePresenter {

        /**
         * 请求场景列表
         */
        void requestSceneList();

        /**
         * 添加场景
         */
        void requestAddScene(String sceneName, String sceneImg);
    }

    interface ISceneModel extends IBaseModel {

        /**
         * 获取场景列表RequestCall
         */
        RequestCall getSceneListRequestCall(Context context);

        /**
         * 获取场景列表
         */
        List<Scene> getSceneList();

        /**
         * 解析场景列表
         */
        void parseSceneList(String jsonStr);

        /**
         * 添加场景
         */
        RequestCall getAddSceneRequestCall(Context context, String sceneName, String sceneImg);

    }
}
