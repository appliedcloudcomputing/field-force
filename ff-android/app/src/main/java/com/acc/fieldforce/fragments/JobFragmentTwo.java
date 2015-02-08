package com.acc.fieldforce.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.adapter.HistoryAdapter;
import com.acc.fieldforce.temp.JobModel;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class JobFragmentTwo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_fragment_two, container , false);
        ArrayList<JobModel> arrayList = JobModel.getModel();
        HistoryAdapter adapter = new HistoryAdapter(getActivity(),arrayList);
        ListView listView = (ListView) view.findViewById(R.id.historyList);
        listView.setAdapter(adapter);
        return view;
    }
}
