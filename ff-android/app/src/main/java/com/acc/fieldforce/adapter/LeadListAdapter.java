package com.acc.fieldforce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.acc.fieldforce.R;
import com.acc.fieldforce.temp.LeadsTemp;
import com.acc.fieldforce.temp.Temp;

import java.util.ArrayList;

/**
 * Created by Nilesh on 2/1/2015.
 */
public class LeadListAdapter extends ArrayAdapter<LeadsTemp> {
    TextView companyName, contactPerson, contactNumber;
    public LeadListAdapter(Context context,ArrayList<LeadsTemp> leadsTemps)  {
        super(context ,0 , leadsTemps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeadsTemp leadsTemps = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.leads_data, parent ,false);
        }

        companyName = (TextView) convertView.findViewById(R.id.company_name);
        contactPerson = (TextView) convertView.findViewById(R.id.contact_person);
        contactNumber = (TextView) convertView.findViewById(R.id.contact_number);

        companyName.setText(leadsTemps.companyName);
        contactPerson.setText(leadsTemps.contactPerson);
        contactNumber.setText(leadsTemps.contactNumber);

        return convertView;
    }

}
