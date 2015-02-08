package com.acc.fieldforce;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.acc.fieldforce.fragments.JobFragmentOne;
import com.acc.fieldforce.fragments.JobFragmentTwo;

/**
 * Created by Sagar on 1/31/2015.
 */
public class JobDescription extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_description_main);

        JobFragmentOne j1 = new JobFragmentOne();
        JobFragmentTwo j2 = new JobFragmentTwo();

        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.frame1, j1, "Details");
        transaction.add(R.id.frame2 , j2 , "History");
        transaction.commit();

    }
}
