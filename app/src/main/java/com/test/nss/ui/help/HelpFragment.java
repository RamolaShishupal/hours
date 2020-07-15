package com.test.nss.ui.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.test.nss.R;
import com.test.nss.TestAdapter;
import com.test.nss.ui.main.MainFragment;

public class HelpFragment extends Fragment {

    private static final String TAG = "HelpFrag";
    View root;
    Toolbar toolbar;
    ImageView home;

    TextView emailPo;
    TextView contactPo;

    ConstraintLayout helpMain;

    Button poButton;
    Button lead1Button;
    Button lead2Button;

    LinearLayout poDetails;
    LinearLayout lead1Details;
    LinearLayout lead2Details;
    TextView toolbarTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_help, container, false);

        toolbarTitle = requireActivity().findViewById(R.id.titleTool);
        toolbarTitle.setText(getString(R.string.main_frag));

        helpMain = root.findViewById(R.id.help_main);

        home = root.findViewById(R.id.homeButton);

        poButton = root.findViewById(R.id.poButton);
        poDetails = root.findViewById(R.id.poDetails);

        lead1Button = root.findViewById(R.id.lead1button);
        lead1Details = root.findViewById(R.id.lead1Details);

        lead2Button = root.findViewById(R.id.lead2Button);
        lead2Details = root.findViewById(R.id.lead2Details);

        emailPo = root.findViewById(R.id.emailPO);
        contactPo = root.findViewById(R.id.contactPO);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = requireActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        home.setVisibility(View.VISIBLE);
        home.setOnClickListener(v -> {
            MainFragment mainFragment = new MainFragment();
            FragmentManager fragmentManager = getFragmentManager();

            assert fragmentManager != null;
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mainFragment).addToBackStack("HelpFrag").commit();

            toolbar.setTitle(getString(R.string.main_frag));
            toolbar.setVisibility(View.VISIBLE);
            onDetach();
        });

        TestAdapter mDbHelper = new TestAdapter(requireContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        //mDbHelper.getHelpData().get(0)
        if (mDbHelper.getHelpData().size() > 0) {
            emailPo.setText(String.format(getString(R.string.email) + ": %s", mDbHelper.getHelpData().get(0)));
            contactPo.setText(String.format(getString(R.string.contact_no) + ": %s", mDbHelper.getHelpData().get(1)));
        } else {
            emailPo.setText(getString(R.string.email));
            contactPo.setText(getString(R.string.contact_no));
        }
        mDbHelper.close();

        helpMain.setOnClickListener(v -> {
            if (poDetails.getVisibility() == View.VISIBLE)
                poDetails.setVisibility(View.GONE);
            if (lead1Details.getVisibility() == View.VISIBLE)
                lead1Details.setVisibility(View.GONE);
            if (lead2Details.getVisibility() == View.VISIBLE)
                lead2Details.setVisibility(View.GONE);
        });

        poButton.setOnClickListener(v -> {
            lead1Details.setVisibility(View.GONE);
            lead2Details.setVisibility(View.GONE);
            poDetails.setVisibility(View.VISIBLE);
        });

        lead1Button.setOnClickListener(v -> {
            lead2Details.setVisibility(View.GONE);
            poDetails.setVisibility(View.GONE);
            lead1Details.setVisibility(View.VISIBLE);
        });

        lead2Button.setOnClickListener(v -> {
            lead1Details.setVisibility(View.GONE);
            poDetails.setVisibility(View.GONE);
            lead2Details.setVisibility(View.VISIBLE);
        });
    }
}