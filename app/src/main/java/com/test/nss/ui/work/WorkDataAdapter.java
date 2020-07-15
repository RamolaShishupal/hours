package com.test.nss.ui.work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nss.R;

import java.util.Collections;
import java.util.List;

public class WorkDataAdapter extends RecyclerView.Adapter<WorkDataAdapter.ViewHolder> {
    List<AdapterDataWork> list = Collections.emptyList();
    Context mCon;

    public WorkDataAdapter(List<AdapterDataWork> list, Context mCon) {
        this.list = list;
        this.mCon = mCon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_view_work, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.natureOfWork.setText(list.get(position).getNature());
        holder.totalHours.setText(list.get(position).getTotalHours());
        holder.compHours.setText(list.get(position).getCompHours());
        holder.remHours.setText(list.get(position).getRemHours());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insert(int position, AdapterDataWork data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(AdapterDataWork data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView natureOfWork;
        public TextView totalHours;
        public TextView compHours;
        public TextView remHours;

        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.natureOfWork = itemView.findViewById(R.id.nature_of_work);
            this.totalHours = itemView.findViewById(R.id.total_hours_work);
            this.compHours = itemView.findViewById(R.id.comp_hours_work);
            this.remHours = itemView.findViewById(R.id.rem_hours_work);

            linearLayout = itemView.findViewById(R.id.workLinear);
        }
    }
}
