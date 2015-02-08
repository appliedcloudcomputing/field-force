package com.acc.fieldforce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.temp.JobsTemp;
import com.acc.fieldforce.temp.LeadsTemp;

import java.util.ArrayList;

/**
 * Created by Nilesh on 2/1/2015.
 */
public class JobListAdapter extends ArrayAdapter<JobsTemp> {
    TextView jobTitle, jobDescription, customerName;
    public JobListAdapter(Context context,ArrayList<JobsTemp> jobsTemps)  {
        super(context ,0 , jobsTemps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobsTemp jobsTemp = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.jobs_data, parent ,false);
        }

        jobTitle = (TextView) convertView.findViewById(R.id.job_title);
        jobDescription = (TextView) convertView.findViewById(R.id.job_description);
        customerName = (TextView) convertView.findViewById(R.id.customer_name);

        jobTitle.setText(jobsTemp.jobTitle);
        jobDescription.setText(jobsTemp.jobDescription);
        customerName.setText(jobsTemp.customerName);

        return convertView;
    }

}
