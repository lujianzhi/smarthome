package com.smarthome.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarthome.R;
import com.smarthome.entity.OperationLog;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Lawson on 2016/5/20.
 */
public class OperationLogAdapter extends RecyclerView.Adapter<OperationLogAdapter.MyViewHolder> {

    private List<OperationLog> operationLogList = new ArrayList<>();

    public OperationLogAdapter() {
    }

    public void setOperationLogList(List<OperationLog> operationLogList) {
        this.operationLogList = operationLogList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_operation_log, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OperationLog operationLog = operationLogList.get(position);

        holder.opTime.setText(operationLog.getOpTime());
        holder.operation.setText(operationLog.getOperation());

    }

    @Override
    public int getItemCount() {
        return operationLogList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView opTime;
        TextView operation;

        public MyViewHolder(View itemView) {
            super(itemView);
            opTime = (TextView) itemView.findViewById(R.id.opTime);
            operation = (TextView) itemView.findViewById(R.id.operation);
        }
    }
}
