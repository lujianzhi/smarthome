package com.smarthome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.R;
import com.smarthome.entity.Equipment;
import com.smarthome.ui.EquipmentDetailActivity;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.MyViewHolder> {

    private Context context;
    private List<Equipment> equipmentList = new ArrayList<>();
    private EquipmentMVPContract.IEquipmentPresenter equipmentPresenter;

    public EquipmentAdapter(Context context, EquipmentMVPContract.IEquipmentPresenter equipmentPresenter) {
        this.context = context;
        this.equipmentPresenter = equipmentPresenter;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_equipment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Equipment equipment = equipmentList.get(position);

        holder.equipment_name.setText(equipment.getName());
        if (equipment.getState() == 1) {
            holder.equipment_status_on.setBackgroundColor(context.getResources().getColor(R.color.button_pressed_color));
            holder.equipment_status_off.setBackgroundColor(context.getResources().getColor(R.color.button_text_color));
        } else {
            holder.equipment_status_on.setBackgroundColor(context.getResources().getColor(R.color.button_text_color));
            holder.equipment_status_off.setBackgroundColor(context.getResources().getColor(R.color.button_pressed_color));
        }

        holder.equipment_status_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipmentPresenter.changeStatus(String.valueOf(equipment.getId()), "1", holder.getAdapterPosition());
                holder.equipment_status_on.setBackgroundColor(context.getResources().getColor(R.color.button_pressed_color));
                holder.equipment_status_off.setBackgroundColor(context.getResources().getColor(R.color.button_text_color));
            }
        });

        holder.equipment_status_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipmentPresenter.changeStatus(String.valueOf(equipment.getId()), "0", holder.getAdapterPosition());
                holder.equipment_status_on.setBackgroundColor(context.getResources().getColor(R.color.button_text_color));
                holder.equipment_status_off.setBackgroundColor(context.getResources().getColor(R.color.button_pressed_color));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EquipmentDetailActivity.class);
                Bundle data = new Bundle();
                data.putString(EquipmentDetailActivity.EQUIPMENT_NAME, equipment.getName());
                data.putString(EquipmentDetailActivity.EQUIPMENT_ID, String.valueOf(equipment.getId()));
                data.putString(EquipmentDetailActivity.EQUIPMENT_STATUS, String.valueOf(equipment.getIsRemind()));
                data.putString(EquipmentDetailActivity.EQUIPMENT_COMMENT, equipment.getEqComment());
                data.putString(EquipmentDetailActivity.SCENE_ID, String.valueOf(equipment.getSceneId()));
                intent.putExtras(data);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView equipment_name;
        Button equipment_status_on;
        Button equipment_status_off;


        public MyViewHolder(View itemView) {
            super(itemView);
            equipment_name = (TextView) itemView.findViewById(R.id.equipment_name);
            equipment_status_on = (Button) itemView.findViewById(R.id.equipment_status_on);
            equipment_status_off = (Button) itemView.findViewById(R.id.equipment_status_off);
        }

    }
}
