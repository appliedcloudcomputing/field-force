package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acc.fieldforce.R;


public class AddLeadsActivity extends Activity {

    private EditText companyName , companyAddress , contactPerson, contactNumber;
    private TextView error;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leads);

        mContext = this;

        companyName = (EditText) findViewById(R.id.c_name);
        companyAddress = (EditText) findViewById(R.id.c_address);
        contactPerson = (EditText) findViewById(R.id.c_person);
        contactNumber = (EditText) findViewById(R.id.c_contact);
        error = (TextView) findViewById(R.id.error);

        error.setGravity(Gravity.CENTER);
        error.setTextColor(0xF7F7F7);

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

        companyAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });

        contactPerson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        String cNameText = companyName.getText().toString();
        String cCompanyText = companyAddress.getText().toString();
        String cAddressText = contactPerson.getText().toString();
        String cContactText = contactNumber.getText().toString();

        error.setBackgroundResource(R.color.orange);
        error.setTextColor(getResources().getColor(R.color.white));
    }

}
