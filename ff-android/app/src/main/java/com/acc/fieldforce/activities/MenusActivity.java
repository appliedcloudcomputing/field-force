package com.acc.fieldforce.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.acc.fieldforce.DrawerActivity;
import com.acc.fieldforce.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


public class MenusActivity extends DrawerActivity {

    private FloatingActionsMenu floatingActionsMenu;
    private Context mContext;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);

        overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_menu, null, false);
        mDrawerLayout.addView(contentView, 0);

        mContext = this;
        getSupportActionBar().setTitle("Menu");
        Button customer = (Button) findViewById(R.id.customer);
        Button leads = (Button) findViewById(R.id.leads);
        Button jobs = (Button) findViewById(R.id.jobs);
        Button expenses = (Button) findViewById(R.id.expenses);
        Button account = (Button) findViewById(R.id.account);

        FloatingActionButton f_customer = (FloatingActionButton) findViewById(R.id.action_a);
        FloatingActionButton f_leads = (FloatingActionButton) findViewById(R.id.action_b);
        FloatingActionButton f_expenses = (FloatingActionButton) findViewById(R.id.action_c);

        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        f_customer.setIcon(R.drawable.ic_customers);
        f_customer.setSize(FloatingActionButton.SIZE_MINI);
        f_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent i = new Intent(mContext, AddCustomerActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
            }
        });

        f_leads.setIcon(R.drawable.ic_leads);
        f_leads.setSize(FloatingActionButton.SIZE_MINI);
        f_leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent i = new Intent(mContext, AddLeadsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
            }
        });

        f_expenses.setIcon(R.drawable.ic_expenses);
        f_expenses.setSize(FloatingActionButton.SIZE_MINI);
        f_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent i = new Intent(mContext, ExpensesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenusActivity.this, CustomerListActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
            }
        });

        leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenusActivity.this, TabActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
            }
        });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenusActivity.this, JobListingActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
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
        overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
    }

    private void startExpense() {
        Intent i = new Intent(this, ListExpensesActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
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

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
