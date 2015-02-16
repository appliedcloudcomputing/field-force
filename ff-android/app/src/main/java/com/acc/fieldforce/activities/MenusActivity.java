package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.acc.fieldforce.R;


public class MenusActivity extends Activity {

    private Button customer, leads, jobs, expenses, account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        customer = (Button) findViewById(R.id.customer);
        leads = (Button) findViewById(R.id.leads);
        jobs = (Button) findViewById(R.id.jobs);
        expenses = (Button) findViewById(R.id.expenses);
        account = (Button) findViewById(R.id.account);

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenusActivity.this, CustomerListActivity.class);
                startActivity(i);
            }
        });

        leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenusActivity.this, LeadsListActivity.class);
                startActivity(i);
            }
        });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenusActivity.this, JobListingActivity.class);
                startActivity(i);
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExpense();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startAccount();
            }
        });
    }

    private void startAccount() {
        Intent i  = new Intent (this, AccountSummaryActivity.class);
        startActivity(i);
    }

    private void startExpense() {
        Intent i = new Intent(this, ListExpensesActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
