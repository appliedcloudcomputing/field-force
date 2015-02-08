package com.acc.fieldforce.temp;

import java.util.ArrayList;

/**
 * Created by Sagar on 1/31/2015.
 */
public class ExpenseModel {
    public String expense;
    public String amount;

    public ExpenseModel(String expense,String amount){
        this.expense=expense;
        this.amount=amount;
    }
    public static ArrayList<ExpenseModel> getModel(){
        ArrayList<ExpenseModel> expenseModels = new ArrayList<ExpenseModel>();
        expenseModels.add(new ExpenseModel("Expense Type","123456"));
        expenseModels.add(new ExpenseModel("Expense Type","123456"));
        expenseModels.add(new ExpenseModel("Expense Type","123456"));
        return expenseModels;
    }
}
