package com.delts.shipitfixit.database;

import android.provider.BaseColumns;

public final class Entries {
    private Entries() {}

    public static class UserEntry implements BaseColumns {
        //Our entire DB name
        public static final String DB_NAME = "Users.db";

        //users database helper
        public static final String TABLE_USERS = "Users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";

        //user information database helper
        public static final String TABLE_USER_INFO = "User_Info";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_BIRTHDAY = "birthday";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_ADDRESS = "address";
    }
}
