package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.adapter.CustomerListAdapter;
import com.acc.fieldforce.temp.Temp;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/27/2015.
 */
public class CustomerListActivity extends Activity{
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        populateList();

        button = (FloatingActionButton) findViewById(R.id.customer_add);
        button.setIcon(R.drawable.ic_add);
        button.setColorNormalResId(R.color.orange);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerListActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateList() {
        ArrayList<Temp> arrayList = Temp.getTemp();
        CustomerListAdapter adapter = new CustomerListAdapter(this, arrayList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_customerlist, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
   /* @Override
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
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide, R.anim.right_slide);
    }
}
