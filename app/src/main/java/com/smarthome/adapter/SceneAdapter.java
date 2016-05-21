package com.smarthome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarthome.R;
import com.smarthome.config.NetConfig;
import com.smarthome.entity.Scene;
import com.smarthome.ui.EquipmentActivity;
import com.smarthome.ui.SceneActivity;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.MyViewHolder> {

    private Context context;
    private List<Scene> sceneList = new ArrayList<>();

    public SceneAdapter(Context context) {
        this.context = context;
    }

    public void setSceneList(List<Scene> sceneList) {
        this.sceneList = sceneList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_scene, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Scene scene = sceneList.get(position);
        Glide.with(context).load(NetConfig.LOCAL + "upload/" + scene.getSceneImg()).into(holder.scenePic);
        holder.sceneName.setText(scene.getSceneName());

        holder.scenePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EquipmentActivity.class);
                Bundle data = new Bundle();
                data.putString(SceneActivity.SCENE_ID, String.valueOf(scene.getId()));
                data.putString(SceneActivity.SCENE_NAME, scene.getSceneName());
                intent.putExtras(data);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sceneList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView scenePic;
        TextView sceneName;

        public MyViewHolder(View itemView) {
            super(itemView);
            scenePic = (ImageView) itemView.findViewById(R.id.scene);
            sceneName = (TextView) itemView.findViewById(R.id.scene_name);
        }
    }
}
