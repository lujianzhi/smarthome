package com.smarthome.AsyncTask;

import android.os.AsyncTask;

import com.smarthome.MVPContract.LogMVPContract;
import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.MVPContract.SceneMVPContract;
import com.smarthome.MVPmodel.IBaseModel;
import com.smarthome.MVPview.IBaseView;
import com.smarthome.entity.Scene;
import com.smarthome.utils.LogUtils;

import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class GetSceneListTask extends AsyncTask<String, Void, List<Scene>> {

    private IBaseModel baseModel;
    private IBaseView baseView;

    public GetSceneListTask(IBaseModel baseModel, IBaseView baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
    }

    @Override
    protected List<Scene> doInBackground(String... params) {
        LogUtils.i("解析场景列表的线程 : " + Thread.currentThread().getName());
        if (baseModel instanceof MainMVPContract.IMainModel) {
            ((MainMVPContract.IMainModel) baseModel).parseSceneList(params[0]);
            return ((MainMVPContract.IMainModel) baseModel).getSceneList();
        } else if (baseModel instanceof SceneMVPContract.ISceneModel) {
            ((SceneMVPContract.ISceneModel) baseModel).parseSceneList(params[0]);
            return ((SceneMVPContract.ISceneModel) baseModel).getSceneList();
        } else {
            ((LogMVPContract.ILogModel) baseModel).parserAllScene(params[0]);
            return ((LogMVPContract.ILogModel) baseModel).getAllScene();
        }
    }

    @Override
    protected void onPostExecute(List<Scene> scenes) {
        if (baseView instanceof MainMVPContract.IMainView) {
            ((MainMVPContract.IMainView) baseView).setSceneList(scenes);
        } else if (baseView instanceof SceneMVPContract.ISceneView) {
            ((SceneMVPContract.ISceneView) baseView).setSceneList(scenes);
        } else {
            ((LogMVPContract.ILogView) baseView).setSceneList(scenes);
        }
        baseView.stopLoading();
    }
}
