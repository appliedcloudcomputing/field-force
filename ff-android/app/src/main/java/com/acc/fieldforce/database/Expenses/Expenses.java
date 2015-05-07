package com.acc.fieldforce.database.Expenses;

public class Expenses {

    String expensesTye;
    String expensesAmount;

    public Expenses (){ }

    public Expenses(String expensesTye, String expensesAmount) {
        this.expensesTye = expensesTye;
        this.expensesAmount = expensesAmount;
    }

    public String getExpensesTye() {
        return expensesTye;
    }

    public void setExpensesTye(String expensesTye) {
        this.expensesTye = expensesTye;
    }

    public String getExpensesAmount() {
        return expensesAmount;
    }

    public void setExpensesAmount(String expensesAmount) {
        this.expensesAmount = expensesAmount;
    }
}
