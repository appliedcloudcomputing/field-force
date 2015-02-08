package com.acc.fieldforce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.acc.fieldforce.adapter.JobListAdapter;
import com.acc.fieldforce.adapter.LeadListAdapter;
import com.acc.fieldforce.temp.JobsTemp;
import com.acc.fieldforce.temp.LeadsTemp;

import java.util.ArrayList;


public class JobListing extends Activity {
    ArrayList<JobsTemp> arrayList;
    JobListAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);

        arrayList = JobsTemp.getTemp();
        listView = (ListView) findViewById(R.id.job_list);
        adapter = new JobListAdapter(this, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startJobDesc();
            }
        });

    }

    private void startJobDesc() {
        Intent i = new Intent(this, JobDescription.class);
        startActivity(i);
    }
}
