package com.smarthome.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class OperationLog implements Parcelable {

    private int id;
    private int userId;
    private String opTime;
    private String operation;
    private int equipmentId;
    private int sceneId;
    private String sceneName;
    private String equipmentName;
    private Equipment equipment;
    private Scene scene;
    private int type;// 0：日志，1：提醒

    protected OperationLog(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        operation = in.readString();
        opTime = in.readString();
        equipmentId = in.readInt();
        sceneId = in.readInt();
        sceneName = in.readString();
        equipmentName = in.readString();
        equipment = in.readParcelable(Equipment.class.getClassLoader());
        scene = in.readParcelable(Scene.class.getClassLoader());
        type = in.readInt();
    }

    public static final Creator<OperationLog> CREATOR = new Creator<OperationLog>() {
        @Override
        public OperationLog createFromParcel(Parcel in) {
            return new OperationLog(in);
        }

        @Override
        public OperationLog[] newArray(int size) {
            return new OperationLog[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OperationLog() {
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(operation);
        dest.writeString(opTime);
        dest.writeInt(equipmentId);
        dest.writeInt(sceneId);
        dest.writeString(sceneName);
        dest.writeString(equipmentName);
        dest.writeParcelable(equipment, flags);
        dest.writeParcelable(scene, flags);
        dest.writeInt(type);
    }
}
