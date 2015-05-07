package com.acc.fieldforce.database.Customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import com.acc.fieldforce.database.Customer.CustomerContract.FeedEntry;

public class CustomerDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "FieldForce.db";

    public CustomerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME +
            " (" +
            CustomerContract.FeedEntry.COLUMN_NAME_NAME + " TEXT PRIMARY KEY " + COMMA_SEP +
            CustomerContract.FeedEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
            CustomerContract.FeedEntry.COLUMN_NAME_COMPANY_NAME + TEXT_TYPE + COMMA_SEP +
            CustomerContract.FeedEntry.COLUMN_NAME_CONTACT_NO + TEXT_TYPE
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

    public void addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NAME, customer.getCustomerName());
        values.put(FeedEntry.COLUMN_NAME_ADDRESS, customer.getCustomerAddress());
        values.put(FeedEntry.COLUMN_NAME_COMPANY_NAME, customer.getCompanyName());
        values.put(FeedEntry.COLUMN_NAME_CONTACT_NO, customer.getContactNumber());

        db.insert(FeedEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Customer getCustomer(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_NAME_NAME,
                        FeedEntry.COLUMN_NAME_ADDRESS, FeedEntry.COLUMN_NAME_COMPANY_NAME, FeedEntry.COLUMN_NAME_CONTACT_NO}, FeedEntry.COLUMN_NAME_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Customer customer = new Customer(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return customer;
    }

    public List<Customer> getAllUsers() {
        List<Customer> customerList = new ArrayList<Customer>();
        String selectQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustomerName(cursor.getString(0));
                customer.setCustomerAddress(cursor.getString(1));
                customer.setCompanyName(cursor.getString(2));
                customer.setContactNumber(cursor.getString(3));

                customerList.add(customer);
            } while (cursor.moveToNext());
        }

        return customerList;
    }

    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NAME, customer.getCustomerName());
        values.put(FeedEntry.COLUMN_NAME_ADDRESS, customer.getCustomerAddress());
        values.put(FeedEntry.COLUMN_NAME_COMPANY_NAME, customer.getCompanyName());
        values.put(FeedEntry.COLUMN_NAME_CONTACT_NO, customer.getContactNumber());

        return db.update(FeedEntry.TABLE_NAME, values, FeedEntry.COLUMN_NAME_NAME + " = ?",
                new String[]{
                        String.valueOf(customer.getCustomerName())
                });
    }

    public void deleteCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_NAME + " = ?",
                new String[]{String.valueOf(customer.getCustomerName())
                });
        db.close();
    }

    public int getCustomersCount() {
        String countQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
