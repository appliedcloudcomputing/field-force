package com.acc.fieldforce.database.Lead;

import android.provider.BaseColumns;

/**
 * Created by Nilesh on 5/7/2015.
 */
public class LeadContract {

    public LeadContract() {
    }

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Lead";
        public static final String COLUMN_NAME_COMPANY_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_CONTACT_PERSON = "contact_person";
        public static final String COLUMN_NAME_CONTACT_NO = "contact_no";
    }
}
