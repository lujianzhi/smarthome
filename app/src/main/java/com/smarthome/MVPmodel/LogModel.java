package com.smarthome.MVPmodel;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smarthome.MVPContract.LogMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.Equipment;
import com.smarthome.entity.OperationLog;
import com.smarthome.entity.Scene;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/21.
 */
public class LogModel implements LogMVPContract.ILogModel {

    private List<Scene> sceneList = new ArrayList<>();
    private List<Equipment> equipmentList = new ArrayList<>();
    private List<OperationLog> operationLogList = new ArrayList<>();

    @Override
    public RequestCall getAllSceneRequestCall(Context context) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "scene_findAll.action")
                .build();
    }

    @Override
    public void parserAllScene(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            Scene scene = gson.fromJson(element, Scene.class);
            sceneList.add(scene);
        }

        Scene top = new Scene();
        top.setId(0);
        top.setSceneName("所有");
        sceneList.add(0, top);

    }

    @Override
    public List<Scene> getAllScene() {
        return sceneList;
    }

    @Override
    public RequestCall getAllEquipmentRequestCall(Context context) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "equipment_findAll.action")
                .build();
    }

    @Override
    public void parseEquipmentList(String jsonStr) {
        equipmentList.clear();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            Equipment equipment = gson.fromJson(element, Equipment.class);
            equipmentList.add(equipment);
        }

        Equipment top = new Equipment();
        top.setId(0);
        top.setName("所有");
        equipmentList.add(0, top);

    }

    @Override
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    @Override
    public RequestCall getAppointedEquipmentRequestCall(Context context, String sceneId) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "equipment_findBySceneId.action")
                .addParams("sceneId", sceneId)
                .build();
    }

    @Override
    public RequestCall getSearchRequestCall(Context context, String startTime, String endTime, String sceneId, String equipmentId, String type, String page, String rows) {
        return OkHttpUtils
                .post()
                .url(NetConfig.getUrl(context) + "operationLog_findByCondition.action")
                .addParams("logPage.sceneId", sceneId)
                .addParams("logPage.eqId", equipmentId)
                .addParams("logPage.startDate", startTime)
                .addParams("logPage.endDate", endTime)
                .addParams("logPage.type", type)
                .addParams("page", page)
                .addParams("rows", rows)
                .build();
    }

    @Override
    public void parseOperationLogList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            OperationLog operationLog = gson.fromJson(element, OperationLog.class);
            operationLogList.add(operationLog);
        }

    }

    @Override
    public List<OperationLog> getOperationLogList() {
        return operationLogList;
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
        operationLogList.clear();
    }
}
