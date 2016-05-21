package com.smarthome.ui;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.smarthome.MVPContract.SceneMVPContract;
import com.smarthome.MVPmodel.SceneModel;
import com.smarthome.MVPpresenter.ScenePresenter;
import com.smarthome.R;
import com.smarthome.adapter.SceneAdapter;
import com.smarthome.entity.Scene;
import com.smarthome.widget.AddSceneDialog;

import java.util.List;

import butterknife.BindView;

/***
 * Created by Lawson on 2016/5/18.
 */
public class SceneActivity extends BaseActivity implements SceneMVPContract.ISceneView {

    public static final String SCENE_ID = "sceneId";
    public static final String SCENE_NAME = "sceneName";

    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.scene_recyclerView)
    RecyclerView sceneRecyclerView;

    private SceneMVPContract.IScenePresenter scenePresenter;
    private SceneAdapter sceneAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        scenePresenter.clearData();
        scenePresenter.requestSceneList();
    }

    @Override
    protected void initViews() {
        sceneAdapter = new SceneAdapter(this);
        sceneRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        sceneRecyclerView.setAdapter(sceneAdapter);

        add.setOnClickListener(this);
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
        scenePresenter = new ScenePresenter(new SceneModel(), this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add:
                showAddSceneDialog();
                break;
        }

    }

    private void showAddSceneDialog() {
        AddSceneDialog addSceneDialog = new AddSceneDialog(this, scenePresenter, this);
        addSceneDialog.setCancelable(false);
        addSceneDialog.setCanceledOnTouchOutside(false);
        addSceneDialog.show();
    }

    @Override
    public void startLoading() {
        showProgressDialog();
    }

    @Override
    public void stopLoading() {
        disMissProgressDialog();
    }

    @Override
    public void notifyDataChanged() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void setSceneList(List<Scene> sceneList) {
        sceneAdapter.setSceneList(sceneList);
        sceneAdapter.notifyDataSetChanged();
    }

    @Override
    public void executeOnResume() {
        onResume();
    }
}
