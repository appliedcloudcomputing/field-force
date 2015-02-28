package com.acc.fieldforce.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.acc.fieldforce.R;


public class LoginActivity extends TabActivity implements TabHost.OnTabChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabHost tabHost;
        Intent intent;

        tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;

        intent = new Intent().setClass(this, SignUpActivity.class);
        spec = tabHost.newTabSpec("Sign Up").setIndicator("Sign Up").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SignInActivity.class);
        spec = tabHost.newTabSpec("Sign In").setIndicator("Sign In").setContent(intent);
        tabHost.addTab(spec);

        tabHost.getTabWidget().setCurrentTab(1);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Helvetica Neue.ttf");

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv1.setTextColor(Color.parseColor("#035c8e"));
            tv1.setAllCaps(false);
            tv1.setTypeface(type);
            tv1.setText("Sign Up");
            tv1.setTextSize(16);
        }

        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#0681BE"));
        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setAllCaps(false);
        tv.setTypeface(type);
        tv.setText("Sign In");
        tv.setTextSize(16);

    }

    @Override
    public void onTabChanged(String tabId) {
        TabHost tabHost;
        tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);
        tabHost.getTabWidget().setCurrentTab(1);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Helvetica Neue.ttf");

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#0681BE"));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setAllCaps(false);
            tv.setTypeface(type);
            tv.setText("Sign In");
            tv.setTextSize(16);
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF"));
        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(android.R.id.title);
        tv1.setTextColor(Color.parseColor("#035c8e"));
        tv1.setAllCaps(false);
        tv1.setTypeface(type);
        tv1.setText("Sign Up");
        tv1.setTextSize(14);
    }
}