package com.acc.fieldforce.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.activities.AddLeadsActivity;
import com.acc.fieldforce.adapter.LeadListAdapter;
import com.acc.fieldforce.temp.LeadsTemp;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Unassigned extends Fragment {
    ArrayList<LeadsTemp> arrayList;
    LeadListAdapter adapter;
    ListView listView;
    FloatingActionButton button;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.unassigned_tab, container, false);
 /*arrayList = LeadsTemp.getTemp();
        listView = (ListView) rootView.findViewById(R.id.unassigned_list);
        adapter = new LeadListAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter);
*/

        button = (FloatingActionButton) v.findViewById(R.id.product_add);
        button.setIcon(R.drawable.ic_add);
        button.setColorNormalResId(R.color.orange);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddLeadsActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}