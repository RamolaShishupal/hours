package com.test.nss.ui.camp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nss.R;

import java.util.Collections;
import java.util.List;

public class CampActListDataAdapter extends RecyclerView.Adapter<CampActListDataAdapter.ViewHolder> {
    List<AdapterCampActList> list = Collections.emptyList();
    Context mCon;

    public CampActListDataAdapter(List<AdapterCampActList> list, Context mCon) {
        this.list = list;
        this.mCon = mCon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_view_camp_act_all_list, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.campActTitle.setText(list.get(position).getCampTitle());
        holder.campActDesc.setText(list.get(position).getCampDesc());
        holder.campActDay.setText(list.get(position).getCampDay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insert(int position, AdapterCampActList data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView campActTitle;
        public TextView campActDesc;
        public TextView campActDay;

        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.campActTitle = itemView.findViewById(R.id.camp_act_list_title);
            this.campActDesc = itemView.findViewById(R.id.camp_act_list_desc);
            this.campActDay = itemView.findViewById(R.id.camp_act_list_day);

            constraintLayout = itemView.findViewById(R.id.campActListAllLinear);
        }
    }
}
