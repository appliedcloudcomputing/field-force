package com.acc.fieldforce.activities;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.acc.fieldforce.R;

/**
 * Created by Sagar on 1/27/2015.
 */
public class AddCustomerActivity extends Activity {
    private  EditText customerName , companyName , customerAddress, contactNumber;
    private TextView error;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        mContext = this;

        customerName = (EditText) findViewById(R.id.c_name);
        companyName = (EditText) findViewById(R.id.c_company);
        customerAddress = (EditText) findViewById(R.id.c_address);
        contactNumber = (EditText) findViewById(R.id.c_contact);
        error = (TextView) findViewById(R.id.error);


        error.setGravity(Gravity.CENTER);
        error.setTextColor(0xF7F7F7);

        customerName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });

        customerAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });

        companyName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });

        contactNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });

        Button save = (Button) findViewById(R.id.c_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void validate() {
        String cNameText = customerName.getText().toString();
        String cCompanyText = companyName.getText().toString();
        String cAddressText = customerAddress.getText().toString();
        String cContactText = contactNumber.getText().toString();

        error.setBackgroundResource(R.color.orange);
        error.setTextColor(getResources().getColor(R.color.white));
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
}
