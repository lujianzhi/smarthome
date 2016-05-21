package com.smarthome.MVPview;

import android.content.Context;

/***
 * Created by Lawson on 2016/5/18.
 */
public interface IBaseView {

    /**
     * 开始加载
     */
    void startLoading();

    /**
     * 结束加载
     */
    void stopLoading();

    /**
     * 界面只有单个列表时数据更新
     */
    void notifyDataChanged();

    /**
     * 获取context
     */
    Context getContext();
}
