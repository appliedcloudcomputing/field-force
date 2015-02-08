package com.acc.fieldforce;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Sagar on 2/1/2015.
 */
public class AccountSummary extends Activity {
    TextView expenses, showExpense , incentivess, showIncentives , total , showTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts_main);

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
}
