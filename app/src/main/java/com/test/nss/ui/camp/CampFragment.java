package com.test.nss.ui.camp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.test.nss.R;
import com.test.nss.ui.main.MainFragment;

public class CampFragment extends Fragment {
    View root;
    Toolbar toolbar;
    ImageView home;

    TextView camp_days;
    TextView camp_details;
    TextView camp_act;
    FragmentManager fm;
    LinearLayout camp_main_details;

    TextView toolbarTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_camp, container, false);

        toolbarTitle = requireActivity().findViewById(R.id.titleTool);
        toolbarTitle.setText(getString(R.string.main_frag));

        fm = requireActivity().getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.popBackStack("CampFrag", 0);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home = root.findViewById(R.id.homeButton);
        home.setVisibility(View.VISIBLE);

        toolbar = requireActivity().findViewById(R.id.toolbar);
        camp_days = root.findViewById(R.id.camp_days);
        camp_act = root.findViewById(R.id.camp_act);
        camp_main_details = root.findViewById(R.id.camp_main_details);
        camp_details = root.findViewById(R.id.camp_details);

        toolbar.setVisibility(View.GONE);
        //camp_main_details.setVisibility(View.VISIBLE);

        home.setOnClickListener(view1 -> {
            MainFragment mainFragment = new MainFragment();
            FragmentManager fragmentManager = getFragmentManager();

            assert fragmentManager != null;
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mainFragment).addToBackStack("CampFrag").commit();
            toolbar.setTitle(getString(R.string.main_frag));
            toolbar.setVisibility(View.VISIBLE);
        });

        camp_act.setOnClickListener(v -> {
            camp_main_details.setVisibility(View.GONE);
            //fragmentManager.beginTransaction().replace(R.id.camp_frag, campDetailsFrag).addToBackStack("CampFragment").commit();
            fm.beginTransaction().replace(R.id.camp_frag, new CampDetailsFrag(), "CampDetailsFrag").addToBackStack("CampFrag").commit();
        });

        camp_details.setOnClickListener(view1 -> {
            camp_main_details.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.camp_frag, new CampDetails(), "CampDetails").addToBackStack("CampFrag").commit();
        });

        camp_days.setOnClickListener(view2 -> {
            camp_main_details.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.camp_frag, new CampActListDetails(), "CampList").addToBackStack("CampFrag").commit();
        });
    }

    @Override
    public void onDetach() {
        camp_main_details.setVisibility(View.GONE);
        super.onDetach();
        if (fm.getBackStackEntryCount() > 0) {
            Log.e("CampFrag", "onDetach: " + fm.getBackStackEntryCount());
            fm.popBackStack("CampFrag", 0);
        }
    }
}