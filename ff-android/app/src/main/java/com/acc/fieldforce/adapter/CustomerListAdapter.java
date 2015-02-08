package com.acc.fieldforce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.temp.Temp;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/27/2015.
 */
public class CustomerListAdapter extends ArrayAdapter<Temp> {
    TextView customerName , companyName , address , contact;
    public CustomerListAdapter(Context context,ArrayList<Temp> temp)  {
        super(context ,0 , temp);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Temp temp = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customerdata, parent ,false);
        }

        customerName = (TextView) convertView.findViewById(R.id.customerName);
        companyName = (TextView) convertView.findViewById(R.id.companyName);
        address = (TextView) convertView.findViewById(R.id.address);
        contact = (TextView) convertView.findViewById(R.id.contact);

        customerName.setText(temp.customerName);
        companyName.setText(temp.companyName);
        address.setText(temp.address);
        contact.setText(temp.phone);

        return convertView;
    }
}
