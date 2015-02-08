package com.acc.fieldforce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sagar on 1/27/2015.
 */
public class AddCustomer extends Activity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);

        button = (Button) findViewById(R.id.c_submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.parseColor("#00AEEF"));
            }
        });
    }

}
