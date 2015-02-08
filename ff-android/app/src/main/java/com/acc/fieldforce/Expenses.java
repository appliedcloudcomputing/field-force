package com.acc.fieldforce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sagar on 1/29/2015.
 */
public class Expenses extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);

        Button b= (Button) findViewById(R.id.button);

    }

}
