package com.smarthome.ui;

import com.smarthome.R;

/***
 * Created by Lawson on 2016/5/18.
 */
public class MessageActivity extends LogActivity {

    @Override
    protected int getTopTitleId() {
        return R.string.message;
    }

    @Override
    public int getNotifyTag() {
        return 1;
    }
}
