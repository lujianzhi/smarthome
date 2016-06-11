package com.smarthome.MVPmodel;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smarthome.MVPContract.SceneMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.Scene;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class SceneModel implements SceneMVPContract.ISceneModel {

    private List<Scene> sceneList = new ArrayList<>();

    @Override
    public RequestCall getSceneListRequestCall(Context context) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "scene_findAll.action")
                .build();
    }

    @Override
    public List<Scene> getSceneList() {
        return sceneList;
    }

    @Override
    public void parseSceneList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            Scene scene = gson.fromJson(element, Scene.class);
            sceneList.add(scene);
        }
    }

    @Override
    public RequestCall getAddSceneRequestCall(Context context, String sceneName, String sceneImg) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "scene_add.action")
                .addParams("scene.sceneName", sceneName)
                .addParams("scene.sceneImg", sceneImg)
                .build();
    }

    @Override
    public int parseJsonCode(String jsonStr) {
        return MyJsonStrUtils.getCode(jsonStr);
    }

    @Override
    public String parseJsonMessage(String jsonStr) {
        return MyJsonStrUtils.getMessage(jsonStr);
    }

    @Override
    public void clearData() {
        sceneList.clear();
    }
}
