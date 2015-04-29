package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.acc.fieldforce.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


public class MenusActivity extends Activity {

    private Button customer, leads, jobs, expenses, account;
    private FloatingActionButton f_customer , f_leads , f_expenses;
    private FloatingActionsMenu floatingActionsMenu;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mContext = this;
        customer = (Button) findViewById(R.id.customer);
        leads = (Button) findViewById(R.id.leads);
        jobs = (Button) findViewById(R.id.jobs);
        expenses = (Button) findViewById(R.id.expenses);
        account = (Button) findViewById(R.id.account);

        f_customer = (FloatingActionButton) findViewById(R.id.action_a);
        f_leads = (FloatingActionButton) findViewById(R.id.action_b);
        f_expenses = (FloatingActionButton) findViewById(R.id.action_c);

        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        f_customer.setIcon(R.drawable.ic_customers);
        f_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent i = new Intent(mContext , AddCustomerActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        f_leads.setIcon(R.drawable.ic_leads);
        f_leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent i = new Intent(mContext , AddLeadsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        f_expenses.setIcon(R.drawable.ic_expenses);
        f_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent i = new Intent(mContext , ExpensesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

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
                Intent i = new Intent(MenusActivity.this, TabActivity.class);
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