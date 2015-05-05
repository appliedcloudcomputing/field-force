package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.ListView;

import com.acc.fieldforce.DrawerActivity;
import com.acc.fieldforce.R;
import com.acc.fieldforce.adapter.ExpenseListAdapter;
import com.acc.fieldforce.temp.ExpenseModel;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class ListExpensesActivity extends DrawerActivity {

    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_expense);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_list_expense, null, false);
        mDrawerLayout.addView(contentView, 0);

        ArrayList<ExpenseModel> arrayList = ExpenseModel.getModel();
        ExpenseListAdapter expenseListAdapter = new ExpenseListAdapter(this , arrayList);
        ListView listView = (ListView) findViewById(R.id.expenseList);
        listView.setAdapter(expenseListAdapter);

        button = (FloatingActionButton) findViewById(R.id.expenses_add);
        button.setIcon(R.drawable.ic_add);
        button.setColorNormalResId(R.color.orange);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExpensesActivity.this, ExpensesActivity.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_customerlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addCustomer:
                addExpense();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addExpense() {
        Intent i = new Intent(this, ExpensesActivity.class);
        startActivity(i);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_slide1, R.anim.left_slide1);
    }
}
