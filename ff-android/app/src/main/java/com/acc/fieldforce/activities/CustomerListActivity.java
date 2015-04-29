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
import android.widget.ListView;

import com.acc.fieldforce.DrawerActivity;
import com.acc.fieldforce.R;
import com.acc.fieldforce.adapter.CustomerListAdapter;
import com.acc.fieldforce.temp.Temp;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/27/2015.
 */
public class CustomerListActivity extends DrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customer_list);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_customer_list, null, false);
        mDrawerLayout.addView(contentView, 0);

        populateList();
        
    }

    private void populateList() {
        ArrayList<Temp> arrayList = Temp.getTemp();
        CustomerListAdapter adapter = new CustomerListAdapter(this, arrayList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
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
                addCustomer();
                return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void addCustomer() {
        Intent i = new Intent(this, AddCustomerActivity.class);
        startActivity(i);
    }
}
