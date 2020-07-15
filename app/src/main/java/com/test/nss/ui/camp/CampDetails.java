package com.test.nss.ui.camp;

import android.database.Cursor;
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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nss.R;
import com.test.nss.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class CampDetails extends Fragment {

    View root;
    List<AdapterCampDetails> campData;
    RecyclerView recCamp;
    LinearLayout camp_main_details;
    Button go_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_camp_details, container, false);
        campData = addCampData();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recCamp = root.findViewById(R.id.camp_details_rec);
        camp_main_details = requireActivity().findViewById(R.id.camp_main_details);
        go_back = root.findViewById(R.id.go_back);

        CampDataAdapter campDataAdapter = new CampDataAdapter(campData, requireContext());
        recCamp.setHasFixedSize(true);
        recCamp.setLayoutManager(new LinearLayoutManager(requireContext()));
        recCamp.setAdapter(campDataAdapter);

        go_back.setOnClickListener(view1 -> {
            camp_main_details.setVisibility(View.VISIBLE);
            onDetach();
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        camp_main_details.setVisibility(View.VISIBLE);
        if (fm.getBackStackEntryCount() > 0) {
            Log.e("CampFrag", "onDetach: " + fm.getBackStackEntryCount());
            fm.popBackStackImmediate(null, 0);
            fm.popBackStack("CampFrag", 0);
        }
    }

    public List<AdapterCampDetails> addCampData() {
        ArrayList<AdapterCampDetails> data2 = new ArrayList<>();

        TestAdapter mDbHelper = new TestAdapter(requireContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor c2 = mDbHelper.getCampDetails();
        Log.e("SSS", "" + c2.getCount());

        while (c2.moveToNext()) {
            //Log.e("camp", c2.getString(c2.getColumnIndex("College_name")));
            data2.add(new AdapterCampDetails(
                    c2.getString(c2.getColumnIndex("College_name")),
                    c2.getString(c2.getColumnIndex("Camp_from")),
                    c2.getString(c2.getColumnIndex("Camp_to")),
                    c2.getString(c2.getColumnIndex("Camp_venue")),
                    c2.getString(c2.getColumnIndex("Camp_post")),
                    c2.getString(c2.getColumnIndex("Camp_taluka")),
                    c2.getString(c2.getColumnIndex("Camp_district"))
            ));
        }
        mDbHelper.close();
        return data2;
    }
}