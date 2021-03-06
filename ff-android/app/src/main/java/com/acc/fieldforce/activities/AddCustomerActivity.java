package com.acc.fieldforce.activities;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acc.fieldforce.R;
import com.acc.fieldforce.database.Customer.Customer;
import com.acc.fieldforce.database.Customer.CustomerDbHelper;

import java.util.List;

public class AddCustomerActivity extends Activity {
    private TextView done, cancel;
    private  EditText customerName , customerAddress, companyName, contactNumber;
    private TextView customerNameError, customerAddressError, companyNameError, contactNumberError;
    private boolean bCustomerName, bCustomerAddress, bCompanyName, bContactNumber;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        done = (TextView) findViewById(R.id.done);
        cancel = (TextView) findViewById(R.id.cancel);

        customerName = (EditText) findViewById(R.id.customer_name);
        companyName = (EditText) findViewById(R.id.company_name);
        customerAddress = (EditText) findViewById(R.id.customer_address);
        contactNumber = (EditText) findViewById(R.id.contact_number);

        customerNameError = (TextView) findViewById(R.id.customer_name_error);
        customerAddressError = (TextView) findViewById(R.id.company_address_error);
        companyNameError = (TextView) findViewById(R.id.company_name_error);
        contactNumberError = (TextView) findViewById(R.id.contact_number_error);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()) {

                    CustomerDbHelper db = new CustomerDbHelper(context);
                    Log.d("Insert: ", "Inserting ..");

                    db.addCustomer(new Customer(customerName.getText().toString(), customerAddress.getText().toString(), companyName.getText().toString(), contactNumber.getText().toString()));

                    Log.d("Reading: ", "Reading all contacts..");

                    List<Customer> user = db.getAllUsers();

                    for (Customer u : user) {

                        String log = "Name: "+u.getCustomerName()+" ,Address: " + u.getCustomerAddress() + " ,Company Name: " + u.getCompanyName() + ", Contact No: " + u.getContactNumber();
                        Log.d("Name: ", log);

                    }
                    Toast.makeText(getBaseContext(), "Customer saved successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddCustomerActivity.this, CustomerListActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Customer discarded",Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddCustomerActivity.this, CustomerListActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private boolean isValidate(){
        final String custName = customerName.getText().toString();
        if (isValidCustomerName(custName)) {
            bCustomerName = false;
            customerNameError.setVisibility(View.VISIBLE);
        }else {
            bCustomerName = true;
            customerNameError.setVisibility(View.GONE);
        }

        final String custAddress = customerAddress.getText().toString();
        if (isValidCustomerAddress(custAddress)) {
            bCustomerAddress = false;
            customerAddressError.setVisibility(View.VISIBLE);
        }else {
            bCustomerAddress = true;
            customerAddressError.setVisibility(View.GONE);
        }

        final String cName = companyName.getText().toString();
        if (isValidCompanyName(cName)) {
            bCompanyName = false;
            companyNameError.setVisibility(View.VISIBLE);
        }else {
            bCompanyName = true;
            companyNameError.setVisibility(View.GONE);
        }

        final String cContact = contactNumber.getText().toString();
        if (!isValidContactNumber(cContact)) {
            bContactNumber = false;
            contactNumberError.setVisibility(View.VISIBLE);
        }else {
            bContactNumber = true;
            contactNumberError.setVisibility(View.GONE);
        }

        if(bCustomerName && bCustomerAddress && bCompanyName && bContactNumber){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidCustomerName(String uName) {
        if(uName.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidCustomerAddress(String uName) {
        if(uName.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidCompanyName(String uName) {
        if(uName.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidContactNumber(String uMobNo) {
        if(uMobNo.equals("") || uMobNo.length()<10){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_customer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_slide1, R.anim.left_slide1);
    }
}
