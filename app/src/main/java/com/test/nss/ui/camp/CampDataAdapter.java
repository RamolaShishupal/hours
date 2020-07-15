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

public class CampDataAdapter extends RecyclerView.Adapter<CampDataAdapter.ViewHolder> {
    List<AdapterCampDetails> list = Collections.emptyList();
    Context mCon;

    public CampDataAdapter(List<AdapterCampDetails> list, Context mCon) {
        this.list = list;
        this.mCon = mCon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_view_camp_details, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.clgName.setText(list.get(position).getClgName());
        holder.campFrom.setText(list.get(position).getCampFrom());
        holder.campTo.setText(list.get(position).getCampTo());
        holder.campVenue.setText(list.get(position).getCampVenue());
        holder.campPost.setText(list.get(position).getCampPost());
        holder.campTaluka.setText(list.get(position).getCampTaluka());
        holder.campDistrict.setText(list.get(position).getCampDistrict());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insert(int position, AdapterCampDetails data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(AdapterCampDetails data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView clgName;
        public TextView campFrom;
        public TextView campTo;
        public TextView campVenue;
        public TextView campPost;
        public TextView campTaluka;
        public TextView campDistrict;

        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.clgName = itemView.findViewById(R.id.clgName);
            this.campFrom = itemView.findViewById(R.id.campFrom);
            this.campTo = itemView.findViewById(R.id.campTo);
            this.campVenue = itemView.findViewById(R.id.campVenue);
            this.campPost = itemView.findViewById(R.id.campPost);
            this.campTaluka = itemView.findViewById(R.id.campTaluka);
            this.campDistrict = itemView.findViewById(R.id.campDistrict);

            constraintLayout = itemView.findViewById(R.id.campDetailsLinear);
        }
    }
}
