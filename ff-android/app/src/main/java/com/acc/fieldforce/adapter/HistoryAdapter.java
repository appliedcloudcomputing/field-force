package com.acc.fieldforce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.temp.JobModel;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class HistoryAdapter extends ArrayAdapter<JobModel> {

    TextView job,description,customer;

    public HistoryAdapter(Context context , ArrayList<JobModel> jobModelArrayList) {
        super(context, 0 ,jobModelArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobModel jobModel = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.job_data, parent , false);
        }

        job = (TextView) convertView.findViewById(R.id.job);
        description=(TextView) convertView.findViewById(R.id.jobDesc);
        customer=(TextView) convertView.findViewById(R.id.jobCust);

        job.setText(jobModel.job);
        description.setText(jobModel.description);
        customer.setText(jobModel.customer);

        return convertView;
    }
}
