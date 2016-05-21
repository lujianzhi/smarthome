package com.smarthome.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smarthome.MVPContract.EquipmentMVPContract;
import com.smarthome.R;
import com.smarthome.entity.Equipment;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.MyViewHolder> {

    private List<Equipment> equipmentList = new ArrayList<>();
    private EquipmentMVPContract.IEquipmentPresenter equipmentPresenter;

    public EquipmentAdapter(EquipmentMVPContract.IEquipmentPresenter equipmentPresenter) {
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
            holder.equipment_status_on.setChecked(true);
        } else {
            holder.equipment_status_off.setChecked(true);
        }

        holder.equipment_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String status = "0";
                switch (checkedId) {
                    case R.id.equipment_status_on:
                        status = "1";
                        break;
                    case R.id.equipment_status_off:
                        status = "0";
                        break;
                }
                equipmentPresenter.changeStatus(String.valueOf(equipment.getId()), status, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView equipment_name;
        RadioGroup equipment_status;
        RadioButton equipment_status_on;
        RadioButton equipment_status_off;

        public MyViewHolder(View itemView) {
            super(itemView);
            equipment_name = (TextView) itemView.findViewById(R.id.equipment_name);
            equipment_status = (RadioGroup) itemView.findViewById(R.id.equipment_status);
            equipment_status_on = (RadioButton) itemView.findViewById(R.id.equipment_status_on);
            equipment_status_off = (RadioButton) itemView.findViewById(R.id.equipment_status_off);
        }
    }
}
