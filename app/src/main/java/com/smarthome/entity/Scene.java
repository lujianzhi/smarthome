package com.smarthome.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Scene implements Parcelable {

    private int id;// 场景id
    private String sceneName;// 场景名称
    private String sceneImg;// 场景图标
    private int userId;// 用户id

    protected Scene(Parcel in) {
        id = in.readInt();
        sceneName = in.readString();
        sceneImg = in.readString();
        userId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(sceneName);
        dest.writeString(sceneImg);
        dest.writeInt(userId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Scene> CREATOR = new Creator<Scene>() {
        @Override
        public Scene createFromParcel(Parcel in) {
            return new Scene(in);
        }

        @Override
        public Scene[] newArray(int size) {
            return new Scene[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneImg() {
        return sceneImg;
    }

    public void setSceneImg(String sceneImg) {
        this.sceneImg = sceneImg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Scene() {
    }

    public Scene(int id, String sceneName, String sceneImg, int userId) {
        super();
        this.id = id;
        this.sceneName = sceneName;
        this.sceneImg = sceneImg;
        this.userId = userId;
    }


}
