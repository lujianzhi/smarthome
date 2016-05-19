package com.smarthome.MVPmodel;

/***
 * Created by Lawson on 2016/5/18.
 */
public interface IBaseModel {

    /**
     * 解析jsonStr的Code
     */
    int parseJsonCode(String jsonStr);

    /**
     * 解析jsonStr的message
     */
    String parseJsonMessage(String jsonStr);
}
