package com.smarthome.MVPmodel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smarthome.MVPContract.EquipmentDetailMVPContract;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.OperationLog;
import com.smarthome.utils.MyJsonStrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/21.
 */
public class EquipmentDetailModel implements EquipmentDetailMVPContract.IEquipmentDetailModel {

    private List<OperationLog> operationLogList = new ArrayList<>();

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

    @Override
    public RequestCall getEquipmentDetailRequestCall(String sceneId, String eqId, String type, String page, String rows) {
        return OkHttpUtils
                .post()
                .url(NetConfig.LOCAL + "operationLog_findByCondition.action")
                .addParams("logPage.sceneId", sceneId)
                .addParams("logPage.eqId", eqId)
                .addParams("logPage.type", type)
                .addParams("page", page)
                .addParams("rows", rows)
                .build();
    }

    @Override
    public void parserEquipmentDetailList(String jsonStr) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(parseJsonMessage(jsonStr)).getAsJsonArray();

        for (JsonElement jsonElement : array) {
            OperationLog operationLog = gson.fromJson(jsonElement, OperationLog.class);
            operationLogList.add(operationLog);
        }
    }

    @Override
    public List<OperationLog> getOperationLogList() {
        return operationLogList;
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
    public String parseStatus(String jsonStr) {
        return MyJsonStrUtils.getMessage(jsonStr);
    }
}
