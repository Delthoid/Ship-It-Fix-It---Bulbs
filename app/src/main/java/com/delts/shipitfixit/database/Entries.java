package com.delts.shipitfixit.database;

import android.provider.BaseColumns;

public final class Entries {
    private Entries() {}

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
