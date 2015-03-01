package com.acc.fieldforce.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acc.fieldforce.R;

/**
 * Created by Sagar on 1/29/2015.
 */
public class ExpensesActivity extends Activity {
    private TextView error;
    private EditText type , amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        type = (EditText) findViewById(R.id.e_type);
        amount = (EditText) findViewById(R.id.e_amount);
        error = (TextView) findViewById(R.id.e_error);

        error.setGravity(Gravity.CENTER);
        error.setTextColor(0xF7F7F7);
        Button b= (Button) findViewById(R.id.e_save);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        type.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });

        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundResource(R.drawable.edittext_focus);
                }else{
                    v.setBackgroundResource(R.drawable.edittextstyle);
                }
            }
        });
    }

    private void validate() {
        error.setBackgroundResource(R.color.orange);
        error.setTextColor(getResources().getColor(R.color.white));

    }

}
