package irama.irama.Sqlite.Tables;

import android.provider.BaseColumns;

/**
 * Created by enagi on 28/6/2017.
 */

public final class feed {
    private feed() {
    }

    public static class feedEntry implements BaseColumns{

        private static final String TABLE_NAME = "client";
        private static final String COLUMN_CLIENT_CODE = "code";
        private static final String COLUMN_CLIENT_NAME = "name";
        private static final String COLUMN_CLIENT_RTN = "rtn";
        private static final String COLUMN_CLIENT_ACTIVE = "active";

        private static final String TEXT_TYPE = " TEXT";
        private static final String BOOLEAN_TYPE = " BOOLEAN";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_CLIENTS =
                "CREATE TABLE " + feed.feedEntry.TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_CLIENT_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_RTN + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_CODE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_ACTIVE + BOOLEAN_TYPE + " )";

        public static final String SQL_DROP_CLIENTS =
                "DROP TABLE IF EXISTS " + feed.feedEntry.TABLE_NAME;
    }

}
