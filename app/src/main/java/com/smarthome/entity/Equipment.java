package com.smarthome.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipment implements Parcelable {

    private int id; // id
    private String name; // 设备名称
    private double value; // 值
    private double highValue; // 阀值上限
    private double lowValue; // 阀值下限
    private int state; // 开关状态 0:关 1：开
    private String image; // 图片
    private String eqComment;// 备注
    private int isRemind; // 是否提醒 0:不提醒 1:提醒
    private int userId; // 设备所对应的用户ID
    private int sceneId; //场景id
    private Scene scene;    //场景
    private int type; //0:开关设备  1：温度  2：水 3：电   4:煤气

    protected Equipment(Parcel in) {
        id = in.readInt();
        name = in.readString();
        value = in.readDouble();
        highValue = in.readDouble();
        lowValue = in.readDouble();
        state = in.readInt();
        image = in.readString();
        eqComment = in.readString();
        isRemind = in.readInt();
        userId = in.readInt();
        sceneId = in.readInt();
        scene = in.readParcelable(Scene.class.getClassLoader());
        type = in.readInt();
    }

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    public void turnOn() {
        this.state = 1;
    }

    public void turnOff() {
        this.state = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getHighValue() {
        return highValue;
    }

    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }

    public double getLowValue() {
        return lowValue;
    }

    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEqComment() {
        return eqComment;
    }

    public void setEqComment(String eqComment) {
        this.eqComment = eqComment;
    }

    public int getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(int isRemind) {
        this.isRemind = isRemind;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Equipment() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Equipment [id=" + id + ", name=" + name + ", value=" + value
                + ", highValue=" + highValue + ", lowValue=" + lowValue
                + ", state=" + state + ", image=" + image + ", eqComment="
                + eqComment + ", isRemind=" + isRemind + ", userId=" + userId
                + ", sceneId=" + sceneId + ", scene=" + scene + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(value);
        dest.writeDouble(highValue);
        dest.writeDouble(lowValue);
        dest.writeInt(state);
        dest.writeString(image);
        dest.writeString(eqComment);
        dest.writeInt(isRemind);
        dest.writeInt(userId);
        dest.writeInt(sceneId);
        dest.writeParcelable(scene, flags);
        dest.writeInt(type);
    }
}
