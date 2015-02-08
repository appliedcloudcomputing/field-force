package com.acc.fieldforce.temp;

import java.util.ArrayList;

/**
 * Created by Nilesh on 2/1/2015.
 */
public class LeadsTemp {
    public String companyName;
    public String contactPerson;
    public String contactNumber;

    public LeadsTemp(String companyName, String contactPerson, String contactNumber){
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.contactNumber = contactNumber;
    }

    public static ArrayList<LeadsTemp> getTemp() {
        ArrayList<LeadsTemp> temp = new ArrayList<LeadsTemp>();
        temp.add(new LeadsTemp("ACC","Mithun","98199"));
        temp.add(new LeadsTemp("ACC", "Sandeep", "7894561"));
        return temp;
    }
}
