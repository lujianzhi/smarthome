package com.smarthome.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.smarthome.MVPContract.SceneMVPContract;
import com.smarthome.R;
import com.smarthome.utils.ToastUtils;

/***
 * Created by Lawson on 2016/5/20.
 */
public class AddSceneDialog extends Dialog implements View.OnClickListener {

    private View mainView;

    private EditText sceneName;

    private String sceneImgName;

    private SceneMVPContract.IScenePresenter scenePresenter;
    private SceneMVPContract.ISceneView sceneView;

    public AddSceneDialog(Context context, SceneMVPContract.IScenePresenter scenePresenter, SceneMVPContract.ISceneView sceneView) {
        super(context, R.style.emptyDialog);
        this.scenePresenter = scenePresenter;
        this.sceneView = sceneView;
        mainView = LayoutInflater.from(context).inflate(R.layout.dialog_add_scene, null);
        setContentView(mainView);
        initViews();
    }

    private void initViews() {
        ImageView zhuwo = (ImageView) mainView.findViewById(R.id.zhuwo);
        ImageView cheku = (ImageView) mainView.findViewById(R.id.cheku);
        ImageView keting = (ImageView) mainView.findViewById(R.id.keting);
        ImageView ciwo = (ImageView) mainView.findViewById(R.id.ciwo);
        ImageView yushi = (ImageView) mainView.findViewById(R.id.yushi);
        ImageView canting = (ImageView) mainView.findViewById(R.id.canting);
        ImageView qita = (ImageView) mainView.findViewById(R.id.qita);
        ButtonRectangle save = (ButtonRectangle) mainView.findViewById(R.id.save);
        ButtonRectangle back = (ButtonRectangle) mainView.findViewById(R.id.back);

        sceneName = (EditText) mainView.findViewById(R.id.scene_name);
        sceneName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sceneName.setSelection(s.length());
            }
        });

        zhuwo.setOnClickListener(this);
        cheku.setOnClickListener(this);
        keting.setOnClickListener(this);
        ciwo.setOnClickListener(this);
        yushi.setOnClickListener(this);
        canting.setOnClickListener(this);
        qita.setOnClickListener(this);
        save.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuwo:
                sceneName.setText(R.string.zhuwo);
                sceneImgName = "zhuwo.png";
                break;
            case R.id.cheku:
                sceneName.setText(R.string.cheku);
                sceneImgName = "cheku.png";
                break;
            case R.id.keting:
                sceneName.setText(R.string.keting);
                sceneImgName = "keting.png";
                break;
            case R.id.ciwo:
                sceneName.setText(R.string.ciwo);
                sceneImgName = "ciwo.png";
                break;
            case R.id.yushi:
                sceneName.setText(R.string.yushi);
                sceneImgName = "yushi.png";
                break;
            case R.id.canting:
                sceneName.setText(R.string.canting);
                sceneImgName = "canting.png";
                break;
            case R.id.qita:
                sceneName.setText(R.string.qita);
                sceneImgName = "qita.png";
                break;
            case R.id.save:
                if (sceneImgName == null || "".equals(sceneName.getText().toString())) {
                    ToastUtils.showShortToast(R.string.input_warn);
                } else {
                    scenePresenter.requestAddScene(sceneName.getText().toString(), sceneImgName);
                    dismiss();
                    sceneView.executeOnResume();
                }
                break;
            case R.id.back:
                dismiss();
                break;
        }
    }
}
