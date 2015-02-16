package com.acc.fieldforce.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.acc.fieldforce.R;

/**
 * Created by Sagar on 1/29/2015.
 */
public class ExpensesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        Button b= (Button) findViewById(R.id.button);

    }

}
