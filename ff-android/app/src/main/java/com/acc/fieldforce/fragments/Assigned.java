package com.acc.fieldforce.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acc.fieldforce.R;
import com.acc.fieldforce.activities.AddLeadsActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class Assigned extends Fragment {

    FloatingActionButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.assigned_tab,container,false);

        button = (FloatingActionButton) v.findViewById(R.id.jobs_add);
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