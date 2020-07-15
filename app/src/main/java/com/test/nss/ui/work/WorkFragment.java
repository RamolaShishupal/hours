package com.test.nss.ui.work;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.test.nss.R;
import com.test.nss.ui.main.MainFragment;

import static com.test.nss.ediary.blackish;
import static com.test.nss.ediary.primaryCol;
import static com.test.nss.ediary.transparent;

public class WorkFragment extends Fragment {

    View root;
    Toolbar toolbar;
    ImageView home;
    Button firstButton;
    Button secButton;
    FragmentManager fm;
    TextView toolbarTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_work, container, false);

        toolbarTitle = requireActivity().findViewById(R.id.titleTool);
        toolbarTitle.setText(getString(R.string.main_frag));

        firstButton = root.findViewById(R.id.firstButton);
        secButton = root.findViewById(R.id.secButton);
        fm = requireActivity().getSupportFragmentManager();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home = root.findViewById(R.id.homeButton);
        toolbar = requireActivity().findViewById(R.id.toolbar);
        home.setVisibility(View.VISIBLE);
        home.setOnClickListener(view1 -> {
            MainFragment mainFragment = new MainFragment();
            FragmentManager fragmentManager = getFragmentManager();

            assert fragmentManager != null;
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mainFragment, mainFragment.getTag()).commit();
            toolbar.setTitle(getString(R.string.main_frag));
            toolbar.setVisibility(View.VISIBLE);
        });
        toolbar.setVisibility(View.GONE);

        firstButton.setOnClickListener(v -> {
            firstButton.setTextColor(Color.WHITE);
            firstButton.setBackgroundColor(primaryCol);
            secButton.setTextColor(blackish);
            secButton.setBackgroundColor(transparent);

            fm.beginTransaction().replace(R.id.work_details, new WorkDetailsFirstFrag()).addToBackStack("MainFrag").commit();
        });

        secButton.setOnClickListener(v -> {
            secButton.setTextColor(Color.WHITE);
            secButton.setBackgroundColor(primaryCol);
            firstButton.setTextColor(blackish);
            firstButton.setBackgroundColor(transparent);

            fm.beginTransaction().replace(R.id.work_details, new WorkDetailsSecFrag()).addToBackStack("MainFrag").commit();
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.popBackStack("WorkFrag", 0);
        }
    }
}