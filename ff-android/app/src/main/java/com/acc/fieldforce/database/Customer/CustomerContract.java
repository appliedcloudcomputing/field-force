package com.acc.fieldforce.database.Customer;

import android.provider.BaseColumns;

public class CustomerContract {

    public CustomerContract() {

    }

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Customer";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_COMPANY_NAME = "company_name";
        public static final String COLUMN_NAME_CONTACT_NO = "contact_no";
    }
}
