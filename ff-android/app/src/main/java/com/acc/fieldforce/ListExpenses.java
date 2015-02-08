package com.acc.fieldforce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.acc.fieldforce.adapter.ExpenseListAdapter;
import com.acc.fieldforce.temp.ExpenseModel;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class ListExpenses extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_expense);

        ArrayList<ExpenseModel> arrayList = ExpenseModel.getModel();
        ExpenseListAdapter expenseListAdapter = new ExpenseListAdapter(this , arrayList);
        ListView listView = (ListView) findViewById(R.id.expenseList);
        listView.setAdapter(expenseListAdapter);

    }

    @Override
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
        Intent i = new Intent(this, Expenses.class);
        startActivity(i);
    }

}
