package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.acc.fieldforce.DrawerActivity;
import com.acc.fieldforce.R;

/**
 * Created by Sagar on 2/1/2015.
 */
public class AccountSummaryActivity extends DrawerActivity {
    TextView expenses, showExpense , incentivess, showIncentives , total , showTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_accounts_main);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_accounts_main, null, false);
        mDrawerLayout.addView(contentView, 0);

        expenses = (TextView) findViewById(R.id.expenses);
        showExpense = (TextView) findViewById(R.id.showExpenses);
        incentivess = (TextView) findViewById(R.id.incentives);
        showIncentives = (TextView) findViewById(R.id.showIncentives);
        total = (TextView) findViewById(R.id.total);
        showTotal = (TextView) findViewById(R.id.showTotal);

        showExpense.setText("1500");
        showIncentives.setText("500");
        showTotal.setText("2000");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
    }
}
