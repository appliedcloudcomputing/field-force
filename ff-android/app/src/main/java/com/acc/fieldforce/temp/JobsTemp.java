package com.acc.fieldforce.temp;

import java.util.ArrayList;

/**
 * Created by Nilesh on 2/1/2015.
 */
public class JobsTemp {
    public String jobTitle;
    public String jobDescription;
    public String customerName;

    public JobsTemp(String jobTitle, String jobDescription, String customerName){
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.customerName = customerName;
    }

    public static ArrayList<JobsTemp> getTemp() {
        ArrayList<JobsTemp> temp = new ArrayList<JobsTemp>();
        temp.add(new JobsTemp("Intranet","add customer provided contents to the intranet","jayesh shaha"));
        temp.add(new JobsTemp("Cloud", "setup infrastructure", "shubho pramanik"));
        return temp;
    }
}
