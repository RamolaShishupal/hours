package com.test.nss.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.test.nss.R;
import com.test.nss.ediary;

import static com.test.nss.ediary.blackish;
import static com.test.nss.ediary.primaryCol;
import static com.test.nss.ediary.transparent;

public class MainFragment extends Fragment {

    View root;
    View line;

    Button firstButton;
    Button secButton;

    ImageView home;
    TextView nssFY;

    Toolbar toolbar;

    FragmentManager fm;
    FrameLayout halvesFrame;
    LinearLayout mainHeader;

    Context mContext;
    TextView toolbarTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = requireContext();

        toolbarTitle = requireActivity().findViewById(R.id.titleTool);
        toolbarTitle.setText(getString(R.string.main_frag));

        fm = requireActivity().getSupportFragmentManager();

        halvesFrame = root.findViewById(R.id.halves_frame);

        firstButton = root.findViewById(R.id.firstButton);
        secButton = root.findViewById(R.id.secButton);

        home = root.findViewById(R.id.homeButton);
        line = root.findViewById(R.id.line_main);
        nssFY = root.findViewById(R.id.nssFY);

        toolbar = root.findViewById(R.id.toolbar);

        mainHeader = root.findViewById(R.id.main_header);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.setVisibility(View.VISIBLE);
                mainHeader.setVisibility(View.GONE);
                firstButton.setTextColor(Color.WHITE);

                firstButton.setBackgroundColor(primaryCol);
                secButton.setTextColor(ediary.blackish);
                secButton.setBackgroundColor(transparent);

                line.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.GONE);

                if (halvesFrame.getVisibility() == View.GONE)
                    halvesFrame.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.halves_frame, new fyAct()).addToBackStack(this.toString()).commit();
            }
        });

        secButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.setVisibility(View.VISIBLE);
                mainHeader.setVisibility(View.GONE);
                secButton.setTextColor(Color.WHITE);
                secButton.setBackgroundColor(primaryCol);
                firstButton.setTextColor(blackish);
                firstButton.setBackgroundColor(transparent);

                line.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.GONE);
                if (halvesFrame.getVisibility() == View.GONE)
                    halvesFrame.setVisibility(View.VISIBLE);

                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.halves_frame, new syAct()).addToBackStack(this.toString()).commit();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home = root.findViewById(R.id.homeButton);
        toolbar = requireActivity().findViewById(R.id.toolbar);

        home.setVisibility(View.INVISIBLE);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toolbar.setTitle(getString(R.string.main_frag));
                toolbar.setVisibility(View.VISIBLE);
                if (mainHeader.getVisibility() == View.GONE || mainHeader.getVisibility() == View.INVISIBLE) {
                    mainHeader.setVisibility(View.VISIBLE);
                }
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fm.popBackStack(this.toString(), 0);
                }
                halvesFrame.setVisibility(View.GONE);
                resetColor();
            }
        });
    }

    private void resetColor() {
        firstButton.setTextColor(blackish);
        firstButton.setBackgroundColor(transparent);
        secButton.setBackgroundColor(transparent);
        secButton.setTextColor(blackish);
        line.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.popBackStack(this.toString(), 0);
        }
    }
}