package irama.irama.Sqlite.Tables;

import android.provider.BaseColumns;

/**
 * Created by enagi on 28/6/2017.
 */

public final class feedSqlite {
    private feedSqlite() {
    }

    public static class feedClient implements BaseColumns{

        private static final String TABLE_NAME = "clients";
        private static final String COLUMN_CLIENT_CODE = "code";
        private static final String COLUMN_CLIENT_NAME = "name";
        private static final String COLUMN_CLIENT_RTN = "rtn";
        private static final String COLUMN_CLIENT_ACTIVE = "active";

        private static final String TEXT_TYPE = " TEXT";
        private static final String BOOLEAN_TYPE = " BOOLEAN";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_CLIENTS =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_CLIENT_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_RTN + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_CODE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_ACTIVE + BOOLEAN_TYPE + " )";

        public static final String SQL_DROP_CLIENTS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class feedProductCategory implements BaseColumns{

        private static final String TABLE_NAME = "categoryproduct";
        private static final String COLUMN_CATEGORY_ID = "categoryId";
        private static final String COLUMN_CATEGORY_NAME = "name";
        private static final String COLUMN_CATEGORY_DESCRIPTION = "description";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_CATEGORY =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_CATEGORY_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CATEGORY_DESCRIPTION + TEXT_TYPE + " )";

        public static final String SQL_DROP_CATEGORY =
                "DROP IF EXISTS " + TABLE_NAME;
    }

    public static final class feedOrder implements BaseColumns{

        private static final String TABLE_NAME = "orders";
        private static final String COLUMN_ORDER_ID = "orderId";
        private static final String COLUMN_ORDER_DESCRIPTION = "description";
        private static final String COLUMN_ORDER_URGENT = "isUrgent";
        private static final String COLUMN_ORDER_REQUEST_ON = "requestOn";
        private static final String COLUMN_ORDER_CODE = "code";
        private static final String COLUMN_ORDER_CLIENT_ID = "clientId";
        private static final String COLUMN_ORDER_PRODUCT_ID = "productId";
        private static final String COLUMN_ORDER_EMPLOYEE_ID = "employeeId";
        private static final String COLUMN_ORDER_SERIE_ID = "serieId";
        private static final String COLUMN_ORDER_ORDER_TYPE = "orderTypetId";

        private static final String TEXT_TYPE = " TEXT";
        private static final String BOOLEAN_TYPE = " BOOLEAN";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ORDER =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_ORDER_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_REQUEST_ON + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_CODE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_CLIENT_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_PRODUCT_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_EMPLOYEE_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_SERIE_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_ORDER_TYPE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ORDER_URGENT + BOOLEAN_TYPE + " )";

        public static final String SQL_DROP_ORDER =
                "DROP IF EXISTS " + TABLE_NAME;

    }

}
