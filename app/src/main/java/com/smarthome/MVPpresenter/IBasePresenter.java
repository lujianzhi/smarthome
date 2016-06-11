package com.smarthome.MVPpresenter;

import android.content.Context;

/***
 * Created by Lawson on 2016/5/18.
 */
public interface IBasePresenter {

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 清除数据
     */
    void clearData();

    /**
     * 获取Context
     */
    Context getContext();
}
