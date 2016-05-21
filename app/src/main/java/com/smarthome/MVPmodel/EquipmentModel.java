package com.smarthome.MVPmodel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.Equipment;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class EquipmentModel implements EquipmentMVPContract.IEquipmentModel {

    private List<Equipment> equipmentList = new ArrayList<>();

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
        equipmentList.clear();
    }

    @Override
    public RequestCall getEquipmentListRequestCall(String sceneId, String rows, String page) {
        return OkHttpUtils
                .post()
                .url(NetConfig.LOCAL + "equipment_findByPage.action")
                .addParams("sceneId", sceneId)
                .addParams("rows", rows)
                .addParams("page", page)
                .build();
    }

    @Override
    public void parseEquipmentList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            Equipment equipment = gson.fromJson(element, Equipment.class);
            equipmentList.add(equipment);
        }
    }

    @Override
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    @Override
    public RequestCall changeStatus(String equipmentId, String state) {
        return OkHttpUtils
                .post()
                .url(NetConfig.LOCAL + "equipment_changeState.action")
                .addParams("equipment.id", equipmentId)
                .addParams("equipment.state", state)
                .build();
    }

    @Override
    public void parseStatus(String jsonStr, String position) {
        equipmentList.get(Integer.valueOf(position)).setState(Integer.valueOf(MyJsonStrUtils.getMessage(jsonStr)));
    }
}
