package com.smarthome.MVPmodel;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smarthome.MVPContract.MainMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.OperationLog;
import com.smarthome.entity.Scene;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class MainModel implements MainMVPContract.IMainModel {

    private List<Scene> sceneList = new ArrayList<>();
    private List<OperationLog> warnInfoList = new ArrayList<>();
    private List<OperationLog> logInfoList = new ArrayList<>();

    @Override
    public RequestCall getSceneRequestCall(Context context) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "scene_findAll.action")
                .build();
    }

    @Override
    public RequestCall getWarnInfoRequestCall(Context context, String type, String page, String rows) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "operationLog_findByCondition.action")
                .addParams("logPage.type", type)
                .addParams("page", page)
                .addParams("rows", rows)
                .build();
    }

    @Override
    public RequestCall getLogMessageRequestCall(Context context, String type, String page, String rows) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "operationLog_findByCondition.action")
                .addParams("logPage.type", type)
                .addParams("page", page)
                .addParams("rows", rows)
                .build();
    }

    @Override
    public RequestCall getTemperatureRequestCall(Context context) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "equipment_findTemperature.action")
                .build();
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
    public void parseWarnInfoList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            OperationLog warnInfo = gson.fromJson(element, OperationLog.class);
            warnInfoList.add(warnInfo);
        }
    }

    @Override
    public void parseLogMessageList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            OperationLog warnInfo = gson.fromJson(element, OperationLog.class);
            logInfoList.add(warnInfo);
        }
    }

    @Override
    public List<Scene> getSceneList() {
        return sceneList;
    }

    @Override
    public List<OperationLog> getWarnInfoList() {
        return warnInfoList;
    }

    @Override
    public List<OperationLog> getLogMessageList() {
        return logInfoList;
    }

    @Override
    public void clearLogMessageList() {
        logInfoList.clear();
    }

    @Override
    public void clearWarmInfoList() {
        warnInfoList.clear();
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
        warnInfoList.clear();
        logInfoList.clear();
    }
}
