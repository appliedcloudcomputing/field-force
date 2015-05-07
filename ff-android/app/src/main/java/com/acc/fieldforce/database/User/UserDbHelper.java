package com.acc.fieldforce.database.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import com.acc.fieldforce.database.User.UserContract.FeedEntry;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "FieldForce.db";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME +
            " (" +
                    FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_EMAIL_ID + " TEXT PRIMARY KEY" + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_MOBILE_NO + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_LOCAIION + TEXT_TYPE
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

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NAME, user.getName());
        values.put(FeedEntry.COLUMN_NAME_EMAIL_ID, user.getEmailId());
        values.put(FeedEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(FeedEntry.COLUMN_NAME_MOBILE_NO, user.getMobile_no());
        values.put(FeedEntry.COLUMN_NAME_LOCAIION, user.getLocation());

        // Inserting Row
        db.insert(FeedEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public User getUser(String emailId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_NAME_NAME,
                        FeedEntry.COLUMN_NAME_EMAIL_ID, FeedEntry.COLUMN_NAME_PASSWORD, FeedEntry.COLUMN_NAME_MOBILE_NO, FeedEntry.COLUMN_NAME_LOCAIION}, FeedEntry.COLUMN_NAME_EMAIL_ID + "=?",
                new String[]{String.valueOf(emailId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setName(cursor.getString(0));
                user.setEmailId(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setMobile_no(cursor.getString(3));
                user.setLocation(cursor.getString(4));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NAME, user.getName());
        values.put(FeedEntry.COLUMN_NAME_EMAIL_ID, user.getEmailId());
        values.put(FeedEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(FeedEntry.COLUMN_NAME_MOBILE_NO, user.getMobile_no());
        values.put(FeedEntry.COLUMN_NAME_LOCAIION, user.getLocation());

        return db.update(FeedEntry.TABLE_NAME, values, FeedEntry.COLUMN_NAME_EMAIL_ID + " = ?",
                new String[]{
                        String.valueOf(user.getEmailId())
                });
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_EMAIL_ID + " = ?",
                new String[]{String.valueOf(user.getEmailId())});
        db.close();
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + FeedEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}