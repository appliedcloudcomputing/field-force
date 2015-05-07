package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acc.fieldforce.R;
import com.acc.fieldforce.database.Customer.Customer;
import com.acc.fieldforce.database.Customer.CustomerDbHelper;
import com.acc.fieldforce.database.Lead.Lead;
import com.acc.fieldforce.database.Lead.LeadDbHelper;

import java.util.List;


public class AddLeadsActivity extends Activity {

    private TextView done, cancel;
    private EditText companyName , companyAddress , contactPerson, contactNumber;
    private TextView companyNameError , companyAddressError , contactPersonError, contactNumberError;
    private boolean bCompanyName, bCompanyAddress, bContactPerson, bContactNumber;
    private TextView error;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leads);

        done = (TextView) findViewById(R.id.done);
        cancel = (TextView) findViewById(R.id.cancel);

        companyName = (EditText) findViewById(R.id.company_name);
        companyAddress = (EditText) findViewById(R.id.company_address);
        contactPerson = (EditText) findViewById(R.id.contact_person_name);
        contactNumber = (EditText) findViewById(R.id.contact_number);

        companyNameError = (TextView) findViewById(R.id.company_name_error);
        companyAddressError = (TextView) findViewById(R.id.company_address_error);
        contactPersonError = (TextView) findViewById(R.id.contact_person_name_error);
        contactNumberError = (TextView) findViewById(R.id.contact_number_error);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()) {

                    LeadDbHelper db = new LeadDbHelper(context);
                    Log.d("Insert: ", "Inserting ..");

                    db.addLead(new Lead(companyName.getText().toString(), companyAddress.getText().toString(), contactPerson.getText().toString(), contactNumber.getText().toString()));

                    Log.d("Reading: ", "Reading all contacts..");

                    List<Lead> lead = db.getAllUsers();

                    for (Lead l : lead) {

                        String log = "Company Name: "+l.getCompanyName()+" ,Address: " + l.getCompanyAddress() + " ,Conact person: " + l.getContactPerson() + ", Contact No: " + l.getContactNumber();
                        Log.d("Name: ", log);

                    }

                    Toast.makeText(getBaseContext(), "Lead saved successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddLeadsActivity.this, TabActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Lead discarded",Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddLeadsActivity.this, TabActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private boolean isValidate(){
        final String cName = companyName.getText().toString();
        if (isValidCompanyName(cName)) {
            bCompanyName = false;
            companyNameError.setVisibility(View.VISIBLE);
        }else {
            bCompanyName = true;
            companyNameError.setVisibility(View.GONE);
        }

        final String cAddress = companyAddress.getText().toString();
        if (isValidCompanyAddress(cAddress)) {
            bCompanyAddress = false;
            companyAddressError.setVisibility(View.VISIBLE);
        }else {
            bCompanyAddress = true;
            companyAddressError.setVisibility(View.GONE);
        }

        final String cPerson = contactPerson.getText().toString();
        if (isValidContactPerson(cPerson)) {
            bContactPerson = false;
            contactPersonError.setVisibility(View.VISIBLE);
        }else {
            bContactPerson = true;
            contactPersonError.setVisibility(View.GONE);
        }

        final String cContact = contactNumber.getText().toString();
        if (!isValidContactNumber(cContact)) {
            bContactNumber = false;
            contactNumberError.setVisibility(View.VISIBLE);
        }else {
            bContactNumber = true;
            contactNumberError.setVisibility(View.GONE);
        }

        if(bCompanyName && bCompanyAddress && bCompanyName && bContactNumber){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidContactPerson(String uName) {
        if(uName.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidCompanyAddress(String uName) {
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
