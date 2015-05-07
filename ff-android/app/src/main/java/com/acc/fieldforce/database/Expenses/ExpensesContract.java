package com.acc.fieldforce.database.Expenses;

import android.provider.BaseColumns;

public class ExpensesContract {

    public ExpensesContract () {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Expenses";
        public static final String COLUMN_NAME_EXPENSES_TYPE = "expenses_type";
        public static final String COLUMN_NAME_EXPENSES_AMOUNT = "expenses_amount";
    }
}
