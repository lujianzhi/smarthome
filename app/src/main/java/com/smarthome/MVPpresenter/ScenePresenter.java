package com.smarthome.MVPpresenter;

import com.smarthome.AsyncTask.GetSceneListTask;
import com.smarthome.MVPContract.SceneMVPContract;
import com.smarthome.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/***
 * Created by Lawson on 2016/5/20.
 */
public class ScenePresenter implements SceneMVPContract.IScenePresenter {
    private final String TAG = ScenePresenter.class.getSimpleName();

    private SceneMVPContract.ISceneModel sceneModel;
    private SceneMVPContract.ISceneView sceneView;

    public ScenePresenter(SceneMVPContract.ISceneModel sceneModel, SceneMVPContract.ISceneView sceneView) {
        this.sceneModel = sceneModel;
        this.sceneView = sceneView;
    }

    @Override
    public void requestSceneList() {
        sceneView.startLoading();
        sceneModel.getSceneListRequestCall().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                sceneView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                GetSceneListTask getSceneListTask = new GetSceneListTask(sceneModel, sceneView);
                getSceneListTask.execute(response);
                sceneView.stopLoading();
            }
        });
    }

    @Override
    public void requestAddScene(String sceneName, String sceneImg) {
        sceneView.stopLoading();
        sceneModel.getAddSceneRequestCall(sceneName, sceneImg).execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtils.e(TAG, e.getMessage());
                sceneView.stopLoading();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.i(TAG, response);
                sceneView.stopLoading();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearData() {
        sceneModel.clearData();
    }
}
