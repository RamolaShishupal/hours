package com.test.nss.ui.main;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nss.R;
import com.test.nss.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.test.nss.ediary.primaryCol;
import static com.test.nss.ediary.transparent;

public class fyAct extends Fragment {

    View root;
    Button univ;
    Button area;
    Button clg;

    LinearLayout mainFy;
    Context mContext;

    RecyclerView univRecFy;
    RecyclerView areaRecFy;
    RecyclerView hoursRecFy;

    List<AdapterDataFy> univListDataFy;
    List<AdapterDataFy> adapterDataFy;
    List<AdapterDataFy> hoursListDataFy;
    LinearLayout linearLayout7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_fy_act, container, false);
        mContext = requireContext();

        // add data dynamically
        adapterDataFy = fill_with_data();
        hoursListDataFy = fill_with_data2();
        univListDataFy = addCampData();

        univ = root.findViewById(R.id.main_univ_fy);
        area = root.findViewById(R.id.main_area_fy);
        clg = root.findViewById(R.id.main_clg_fy);

        mainFy = root.findViewById(R.id.main_fy);
        linearLayout7 = root.findViewById(R.id.linearLayout7);

        univRecFy = root.findViewById(R.id.univRecFy);
        areaRecFy = root.findViewById(R.id.areaRecFy);
        hoursRecFy = root.findViewById(R.id.hoursRecFy);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*TestAdapter mDbHelper = new TestAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        ArrayList<String> c = mDbHelper.getActList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.simple_list_item_2, c);
        listView.setAdapter(adapter);

        ArrayList<String> c2 = mDbHelper.getActType();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mContext, R.layout.simple_list_item_2, c2);
        listView2.setAdapter(adapter2);

        ArrayList<String> c3 = mDbHelper.getActType();
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mContext, R.layout.simple_list_item_2, c3);
        listView3.setAdapter(adapter3);

        mDbHelper.close();*/

        univ.setOnClickListener(v -> {
            mainFy.setVisibility(View.VISIBLE);
            univRecFy.setVisibility(View.VISIBLE);
            areaRecFy.setVisibility(View.GONE);
            hoursRecFy.setVisibility(View.GONE);
            linearLayout7.setVisibility(View.VISIBLE);

            univ.setBackgroundColor(primaryCol);
            univ.setTextColor(Color.WHITE);
            area.setBackgroundColor(transparent);
            area.setTextColor(Color.BLACK);
            clg.setBackgroundColor(transparent);
            clg.setTextColor(Color.BLACK);
        });

        area.setOnClickListener(v -> {
            mainFy.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.VISIBLE);

            univRecFy.setVisibility(View.GONE);
            areaRecFy.setVisibility(View.VISIBLE);
            hoursRecFy.setVisibility(View.GONE);

            area.setBackgroundColor(primaryCol);
            area.setTextColor(Color.WHITE);
            univ.setBackgroundColor(transparent);
            univ.setTextColor(Color.BLACK);
            clg.setBackgroundColor(transparent);
            clg.setTextColor(Color.BLACK);
        });

        clg.setOnClickListener(v -> {
            mainFy.setVisibility(View.VISIBLE);

            linearLayout7.setVisibility(View.VISIBLE);
            univRecFy.setVisibility(View.GONE);
            areaRecFy.setVisibility(View.GONE);
            hoursRecFy.setVisibility(View.VISIBLE);

            clg.setBackgroundColor(primaryCol);
            clg.setTextColor(Color.WHITE);
            univ.setBackgroundColor(transparent);
            univ.setTextColor(Color.BLACK);
            area.setBackgroundColor(transparent);
            area.setTextColor(Color.BLACK);
        });

        RecyclerView recyclerViewUniv = root.findViewById(R.id.univRecFy);
        MyListAdapterFy adapterUniv = new MyListAdapterFy(univListDataFy, mContext);
        recyclerViewUniv.setHasFixedSize(true);
        recyclerViewUniv.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewUniv.setAdapter(adapterUniv);

        RecyclerView recyclerViewArea = root.findViewById(R.id.areaRecFy);
        MyListAdapterFy adapterArea = new MyListAdapterFy(adapterDataFy, mContext);
        recyclerViewArea.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewArea.setAdapter(adapterArea);

        RecyclerView recyclerViewHours = root.findViewById(R.id.hoursRecFy);
        MyListAdapterFy adapterHours = new MyListAdapterFy(hoursListDataFy, mContext);
        recyclerViewHours.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewHours.setAdapter(adapterHours);
    }

    // TODO: Check this
    @Override
    public void onDetach() {
        super.onDetach();
        linearLayout7.setVisibility(View.GONE);
        /*FragmentManager fm = requireActivity().getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }*/
    }

    public List<AdapterDataFy> fill_with_data() {

        // TODO: Switch to activity or new frag for showing more data
        ArrayList<AdapterDataFy> data = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            data.add(new AdapterDataFy("DD/MM/YYYY", "Activity " + i, "00"));
        }
        /*for (int i = 1; i <= 7; i++) {
            data.add(new AdapterDataFy("", "", ""));
        }*/

        return data;
    }

    public List<AdapterDataFy> fill_with_data2() {
        ArrayList<AdapterDataFy> data2 = new ArrayList<>();

        // TODO: Switch to activity or new frag for showing more data
        for (int i = 1; i <= 30; i++) {
            data2.add(new AdapterDataFy("DD/MM/YYYY", "Activity " + i, "00"));
        }
        return data2;
    }

    public List<AdapterDataFy> addCampData() {
        ArrayList<AdapterDataFy> data2 = new ArrayList<>();

        TestAdapter mDbHelper = new TestAdapter(requireContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor c2 = mDbHelper.getActList();
        Log.e("SSS", "" + c2.getCount());

        while (c2.moveToNext()) {
            //Log.e("camp", c2.getString(c2.getColumnIndex("College_name")));
            data2.add(new AdapterDataFy(
                    c2.getString(c2.getColumnIndex("AssignedDate")),
                    c2.getString(c2.getColumnIndex("ActivityName")),
                    c2.getString(c2.getColumnIndex("HoursAssigned")
                    )));
        }
        mDbHelper.close();
        return data2;
    }
}