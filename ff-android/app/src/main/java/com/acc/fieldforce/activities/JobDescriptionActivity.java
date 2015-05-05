package com.acc.fieldforce.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.acc.fieldforce.R;
import com.acc.fieldforce.fragments.JobFragmentOne;
import com.acc.fieldforce.fragments.JobFragmentTwo;

/**
 * Created by Sagar on 1/31/2015.
 */
public class JobDescriptionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description_main);

        JobFragmentOne j1 = new JobFragmentOne();
        JobFragmentTwo j2 = new JobFragmentTwo();

        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.frame1, j1, "Details");
        transaction.add(R.id.frame2 , j2 , "History");
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_slide1, R.anim.left_slide1);
    }
}
