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
import com.test.nss.ui.onClickInterface;

import java.util.Collections;
import java.util.List;

public class CampActDataAdapter extends RecyclerView.Adapter<CampActDataAdapter.ViewHolder> {
    List<AdapterCampAct> list = Collections.emptyList();
    Context mCon;
    onClickInterface onClickInterface;

    public CampActDataAdapter(List<AdapterCampAct> list, Context mCon, onClickInterface onClickInterface) {
        this.list = list;
        this.mCon = mCon;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_view_camp_act_list, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.campListName.setText(list.get(position).getCampListName());
        holder.campListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickInterface.setClick(list.get(position).getCampListName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insert(int position, AdapterCampAct data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(AdapterCampAct data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView campListName;

        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.campListName = itemView.findViewById(R.id.camp_act_name);

            constraintLayout = itemView.findViewById(R.id.campActListLinear);
        }
    }
}
