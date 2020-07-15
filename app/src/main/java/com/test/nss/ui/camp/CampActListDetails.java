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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nss.R;
import com.test.nss.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class CampActListDetails extends Fragment {

    View root;
    List<AdapterCampActList> campData;
    RecyclerView recCampList;
    Button go_back;
    LinearLayout camp_main_details;
    ConstraintLayout campActAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_camp_activity_all, container, false);
        campData = addCampData();
        go_back = root.findViewById(R.id.go_back);
        camp_main_details = requireActivity().findViewById(R.id.camp_main_details);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recCampList = root.findViewById(R.id.recCampListAll);
        CampActListDataAdapter campDataAdapter = new CampActListDataAdapter(campData, requireContext());
        recCampList.setHasFixedSize(true);
        recCampList.setLayoutManager(new LinearLayoutManager(requireContext()));
        recCampList.setAdapter(campDataAdapter);
        campActAll = root.findViewById(R.id.campActAll);

        go_back.setOnClickListener(view1 -> {
            camp_main_details.setVisibility(View.VISIBLE);
            onDetach();
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        camp_main_details.setVisibility(View.VISIBLE);
        campActAll.setVisibility(View.GONE);
    }

    public List<AdapterCampActList> addCampData() {
        ArrayList<AdapterCampActList> data2 = new ArrayList<>();

        TestAdapter mDbHelper = new TestAdapter(requireContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor c2 = mDbHelper.getCampActListAll();
        Log.e("SSS", "" + c2.getCount());

        while (c2.moveToNext()) {
            //Log.e("camp", c2.getString(c2.getColumnIndex("College_name")));
            data2.add(new AdapterCampActList(
                    c2.getString(c2.getColumnIndex("CampActivityTitle")),
                    c2.getString(c2.getColumnIndex("CampActivityDescription")),
                    c2.getString(c2.getColumnIndex("CampDay"))
            ));
        }
        mDbHelper.close();
        return data2;
    }
}