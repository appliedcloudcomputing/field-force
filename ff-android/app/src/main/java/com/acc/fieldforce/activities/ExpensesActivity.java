package com.acc.fieldforce.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acc.fieldforce.R;
import com.acc.fieldforce.database.Expenses.Expenses;
import com.acc.fieldforce.database.Expenses.ExpensesDbHelper;

import java.util.List;

public class ExpensesActivity extends Activity {

    private TextView done, cancel;
    private  EditText expensesType, expensesAmount;
    private  TextView expensesTypeError, expensesAmountError;
    private boolean bExpensesType, bExpensesAmount;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        done = (TextView) findViewById(R.id.done);
        cancel = (TextView) findViewById(R.id.cancel);

        expensesType = (EditText) findViewById(R.id.expenses_type);
        expensesAmount = (EditText) findViewById(R.id.expenses_amount);

        expensesTypeError = (TextView) findViewById(R.id.expenses_type_error);
        expensesAmountError = (TextView) findViewById(R.id.expenses_amount_error);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()) {

                    ExpensesDbHelper db = new ExpensesDbHelper(context);
                    Log.d("Insert: ", "Inserting ..");

                    db.addExpenses(new Expenses(expensesType.getText().toString(), expensesAmount.getText().toString()));

                    Log.d("Reading: ", "Reading all contacts..");

                    List<Expenses> lead = db.getAllExpenses();

                    for (Expenses e : lead) {

                        String log = "Expenses Type: "+e.getExpensesTye()+" ,Expenses Amount: " + e.getExpensesAmount();
                        Log.d("Name: ", log);

                    }

                    Toast.makeText(getBaseContext(), "Expenses saved successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ExpensesActivity.this, ListExpensesActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Customer discarded",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ExpensesActivity.this, ListExpensesActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private boolean isValidate(){
        final String eType = expensesType.getText().toString();
        if (isValidExpensesType(eType)) {
            bExpensesType = false;
            expensesTypeError.setVisibility(View.VISIBLE);
        }else {
            bExpensesType = true;
            expensesTypeError.setVisibility(View.GONE);
        }

        final String eAmount = expensesAmount.getText().toString();
        if (!isValidExpensesAmount(eAmount)) {
            bExpensesAmount = false;
            expensesAmountError.setVisibility(View.VISIBLE);
        }else {
            bExpensesAmount = true;
            expensesAmountError.setVisibility(View.GONE);
        }

        if(bExpensesType && bExpensesAmount){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidExpensesType(String eType) {
        if(eType.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidExpensesAmount(String eAmt) {
        if(eAmt.equals("") || eAmt.length()<0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_slide1, R.anim.left_slide1);
    }

}
