package com.acc.fieldforce.database.User;

import android.provider.BaseColumns;

/**
 * Created by Nilesh on 5/7/2015.
 */
public class UserContract {

    public UserContract() {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL_ID = "email_id";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_MOBILE_NO = "mobile_no";
        public static final String COLUMN_NAME_LOCAIION = "location";
    }
}
