package com.acc.fieldforce.temp;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/29/2015.
 */
public class Temp {
    public String customerName;
    public String companyName;
    public String address;
    public String phone;

    public Temp(String cusName , String coName , String add , String cont){
        this.customerName = cusName;
        this.companyName = coName;
        this.address = add;
        this.phone = cont;
    }

    public static ArrayList<Temp> getTemp() {
        ArrayList<Temp> temp = new ArrayList<Temp>();
        temp.add(new Temp("Swapnil","ACC","pokhran road1, Thane","123456"));
        temp.add(new Temp("Sandeep","Apex","BKC ,Bandra", "7894561"));
        return temp;
    }
}
