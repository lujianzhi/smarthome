package com.smarthome.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.smarthome.R;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class SceneActivity extends BaseActivity {
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.scene_recyclerView)
    RecyclerView sceneRecyclerView;

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scene;
    }

    @Override
    protected int getTopTitleId() {
        return R.string.scene;
    }

    @Override
    protected void initPresenterAndModel() {

    }

    @Override
    public void onClick(View v) {

    }
}
