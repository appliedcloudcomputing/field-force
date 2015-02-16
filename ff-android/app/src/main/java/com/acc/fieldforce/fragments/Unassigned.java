package com.acc.fieldforce.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.adapter.LeadListAdapter;
import com.acc.fieldforce.temp.LeadsTemp;

import java.util.ArrayList;

/**
 * Created by Nilesh on 1/31/2015.
 */
public class Unassigned extends Fragment {
    ArrayList<LeadsTemp> arrayList;
    LeadListAdapter adapter;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.unassigned, container, false);
        arrayList = LeadsTemp.getTemp();
        listView = (ListView) rootView.findViewById(R.id.unassigned_list);
        adapter = new LeadListAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter);

        return rootView;
    }
}