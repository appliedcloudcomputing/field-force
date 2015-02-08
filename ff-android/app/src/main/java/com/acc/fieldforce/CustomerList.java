package com.acc.fieldforce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.acc.fieldforce.adapter.CustomerListAdapter;
import com.acc.fieldforce.temp.Temp;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/27/2015.
 */
public class CustomerList extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerlist);
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
        Intent i = new Intent(this, AddCustomer.class);
        startActivity(i);
    }
}
