package com.acc.fieldforce.temp;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class JobModel {
   public String job;
    public String description;
    public String customer;

    public JobModel(String job, String description , String customer){
        this.job=job;
        this.description=description;
        this.customer=customer;
    }

    public static ArrayList<JobModel> getModel(){
        ArrayList<JobModel> jobModels = new ArrayList<JobModel>();
        jobModels.add(new JobModel("Test Job","Some Description","Customer name"));
        jobModels.add(new JobModel("Test Job","Some Description","Customer name"));
        jobModels.add(new JobModel("Test Job","Some Description","Customer name"));
        return jobModels;
    }
}
