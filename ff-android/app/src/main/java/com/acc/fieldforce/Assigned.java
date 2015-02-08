package com.acc.fieldforce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.acc.fieldforce.adapter.LeadListAdapter;
import com.acc.fieldforce.temp.LeadsTemp;

import java.util.ArrayList;

/**
 * Created by Nilesh on 1/31/2015.
 */
public class Assigned extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.assigned, container, false);;
        return rootView;
    }
}
