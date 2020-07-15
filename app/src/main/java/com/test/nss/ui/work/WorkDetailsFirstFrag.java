package com.test.nss.ui.work;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nss.R;
import com.test.nss.TestAdapter;
import com.test.nss.ui.camp.AdapterCampDetails;

import java.util.ArrayList;
import java.util.List;


public class WorkDetailsFirstFrag extends Fragment {
    View root;
    ListView compHours;
    FragmentManager fm;
    List<AdapterDataWork> workListData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_work_details_first, container, false);
        workListData = firstHalfWorkData();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewWork = root.findViewById(R.id.firstWorkRec);

        WorkDataAdapter adapterWork = new WorkDataAdapter(workListData, requireContext());
        recyclerViewWork.setHasFixedSize(true);
        recyclerViewWork.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewWork.setAdapter(adapterWork);
        /*ArrayList<HashMap<String, String>> formList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray m_jArry = obj.getJSONArray("formules");
            HashMap<String, String> m_li;
            m_li = new HashMap<>();
            ArrayList<String> m = new ArrayList<>();
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String formula_value = jo_inside.getString("formule");
                String url_value = jo_inside.getString("url");
                String shit_val = jo_inside.getString("shit");
                String x = jo_inside.getString("chair");
                //Log.d("Details-->", formula_value);
                //Log.d("Details-->", url_value);
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);
                m_li.put("shit", shit_val);
                m_li.put("chair", x);

                formList.add(m_li);
                //Log.d("Form-->", Objects.requireNonNull(formList.get(i).get("formule")));
                Log.d("Form-->", Objects.requireNonNull(formList.get(i).get("url")));
                Log.d("Form-->", Objects.requireNonNull(formList.get(i).get("shit")));
                Log.d("Form-->", Objects.requireNonNull(formList.get(i).get("chair")));
                //Log.d("FormList-->", Objects.requireNonNull(formList.get(i).get("formule")));

                m.add(formList.get(i).get("url"));
                //ListAdapter adapter = new SimpleAdapter(requireContext(), as.get(i).get("url"), android.R.layout.simple_list_item_1,new String[]{"url"}, new int[]{R.id.url});
                ArrayAdapter adapter = new ArrayAdapter(requireContext(), R.layout.simple_list_item_1, m);
                compHours.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = requireActivity().getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fm = requireActivity().getSupportFragmentManager();
        fm.popBackStack("WorkFrag", 0);
    }

    public List<AdapterDataWork> firstHalfWorkData() {
        ArrayList<AdapterDataWork> data = new ArrayList<>();

        TestAdapter mDbHelper = new TestAdapter(requireContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor c2 = mDbHelper.getCampDetails();
        Log.e("SSS", "" + c2.getCount());

        data.add(new AdapterDataWork(
                c2.getString(c2.getColumnIndex("nature")),
                c2.getString(c2.getColumnIndex("totalHours")),
                c2.getString(c2.getColumnIndex("compHours")),
                c2.getString(c2.getColumnIndex("remHours"))

        // TODO: Switch to activity or new frag for showing more data
//      data.add(new AdapterDataWork("Area Based Project 1", "40", "00", "40"));
//        data.add(new AdapterDataWork("University/District", "10", "00", "10"));
//        data.add(new AdapterDataWork("College Work", "10", "00", "10"));
        ));

        mDbHelper.close();
        return data;
    }
}