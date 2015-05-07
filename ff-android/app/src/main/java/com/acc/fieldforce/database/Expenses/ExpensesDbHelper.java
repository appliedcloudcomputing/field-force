package com.acc.fieldforce.database.Expenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acc.fieldforce.database.Expenses.ExpensesContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class ExpensesDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "FieldForce.db";

    public ExpensesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME +
            " (" +
            FeedEntry.COLUMN_NAME_EXPENSES_TYPE+ " TEXT PRIMARY KEY " + COMMA_SEP +
            FeedEntry.COLUMN_NAME_EXPENSES_AMOUNT + TEXT_TYPE
            +" )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
        onCreate(db);
    }

    public void addExpenses(Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_EXPENSES_TYPE, expenses.getExpensesTye());
        values.put(FeedEntry.COLUMN_NAME_EXPENSES_AMOUNT, expenses.getExpensesAmount());

        db.insert(FeedEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Expenses getExpenses(String expenses) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_NAME_EXPENSES_TYPE,
                        FeedEntry.COLUMN_NAME_EXPENSES_AMOUNT}, FeedEntry.COLUMN_NAME_EXPENSES_TYPE + "=?",
                new String[]{String.valueOf(expenses)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Expenses expenses1 = new Expenses(cursor.getString(0), cursor.getString(1));

        return expenses1;
    }

    public List<Expenses> getAllExpenses() {
        List<Expenses> customerList = new ArrayList<Expenses>();
        String selectQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Expenses expenses = new Expenses();
                expenses.setExpensesTye(cursor.getString(0));
                expenses.setExpensesAmount(cursor.getString(1));

                customerList.add(expenses);
            } while (cursor.moveToNext());
        }

        return customerList;
    }

    public int updateExpenses(Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_EXPENSES_TYPE, expenses.getExpensesTye());
        values.put(FeedEntry.COLUMN_NAME_EXPENSES_AMOUNT, expenses.getExpensesAmount());

        return db.update(FeedEntry.TABLE_NAME, values, FeedEntry.COLUMN_NAME_EXPENSES_TYPE + " = ?",
                new String[]{
                        String.valueOf(expenses.getExpensesTye())
                });
    }

    public void deleteExpenses(Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_EXPENSES_TYPE + " = ?",
                new String[]{String.valueOf(expenses.getExpensesTye())
                });
        db.close();
    }

    public int getExpensesCount() {
        String countQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}
