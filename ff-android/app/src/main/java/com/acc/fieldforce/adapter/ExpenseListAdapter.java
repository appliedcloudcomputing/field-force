package com.acc.fieldforce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.temp.ExpenseModel;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class ExpenseListAdapter extends ArrayAdapter<ExpenseModel> {

    TextView expense, amount;
    ImageView indicator;

    public ExpenseListAdapter(Context context, ArrayList<ExpenseModel> expenseModelArrayList) {
        super(context, 0 , expenseModelArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpenseModel expenseModel=getItem(position);

        if(convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.expense_data,parent,false);
        }

        expense = (TextView) convertView.findViewById(R.id.listType);
        amount = (TextView) convertView.findViewById(R.id.listAmount);
        indicator = (ImageView) convertView.findViewById(R.id.indicator);

        expense.setText(expenseModel.expense);
        amount.setText(expenseModel.amount);

        return convertView;
    }
}
