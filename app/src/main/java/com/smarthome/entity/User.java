package com.smarthome.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private int id; // 用户ID
    private String userName; // 用户名
    private String password;// 密码
    private String telephone;// 手机号码

    protected User(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        password = in.readString();
        telephone = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(telephone);
    }
}
