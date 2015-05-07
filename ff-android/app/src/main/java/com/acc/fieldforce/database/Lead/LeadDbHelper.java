package com.acc.fieldforce.database.Lead;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acc.fieldforce.database.Customer.Customer;
import com.acc.fieldforce.database.Customer.CustomerContract;
import com.acc.fieldforce.database.Lead.LeadContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class LeadDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "FieldForce.db";

    public LeadDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME +
            " (" +
            FeedEntry.COLUMN_NAME_COMPANY_NAME + " TEXT PRIMARY KEY " + COMMA_SEP +
            FeedEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
            FeedEntry.COLUMN_NAME_CONTACT_PERSON + TEXT_TYPE + COMMA_SEP +
            FeedEntry.COLUMN_NAME_CONTACT_NO + TEXT_TYPE
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

    public void addLead(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_COMPANY_NAME, lead.getCompanyName());
        values.put(FeedEntry.COLUMN_NAME_ADDRESS, lead.getCompanyAddress());
        values.put(FeedEntry.COLUMN_NAME_CONTACT_PERSON, lead.getContactPerson());
        values.put(FeedEntry.COLUMN_NAME_CONTACT_NO, lead.getContactNumber());

        db.insert(FeedEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Lead getLead(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_NAME_COMPANY_NAME,
                        FeedEntry.COLUMN_NAME_ADDRESS, FeedEntry.COLUMN_NAME_CONTACT_PERSON, FeedEntry.COLUMN_NAME_CONTACT_NO}, FeedEntry.COLUMN_NAME_COMPANY_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Lead lead = new Lead(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return lead;
    }

    public List<Lead> getAllUsers() {
        List<Lead> leadList = new ArrayList<Lead>();
        String selectQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Lead lead = new Lead();
                lead.setCompanyName(cursor.getString(0));
                lead.setCompanyAddress(cursor.getString(1));
                lead.setContactPerson(cursor.getString(2));
                lead.setContactNumber(cursor.getString(3));

                leadList.add(lead);
            } while (cursor.moveToNext());
        }

        return leadList;
    }

    public int updateLead(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_COMPANY_NAME, lead.getCompanyName());
        values.put(FeedEntry.COLUMN_NAME_ADDRESS, lead.getCompanyAddress());
        values.put(FeedEntry.COLUMN_NAME_CONTACT_PERSON, lead.getContactPerson());
        values.put(FeedEntry.COLUMN_NAME_CONTACT_NO, lead.getContactNumber());

        return db.update(FeedEntry.TABLE_NAME, values, FeedEntry.COLUMN_NAME_COMPANY_NAME + " = ?",
                new String[]{
                        String.valueOf(lead.getCompanyName())
                });
    }

    public void deleteLead(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_COMPANY_NAME + " = ?",
                new String[]{String.valueOf(lead.getCompanyName())
                });
        db.close();
    }

    public int getLeadsCount() {
        String countQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
