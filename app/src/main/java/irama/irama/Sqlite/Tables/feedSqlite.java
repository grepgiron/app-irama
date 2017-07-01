package irama.irama.Sqlite.Tables;

import android.provider.BaseColumns;

/**
 * Created by enagi on 28/6/2017.
 */

public final class feedSqlite {
    private feedSqlite() {
    }

    public static class feedClient implements BaseColumns{

        public static final String TABLE_NAME = "clients";
        public static final String COLUMN_CLIENT_CODE = "code";
        public static final String COLUMN_CLIENT_ID = "clientId";
        public static final String COLUMN_CLIENT_NAME = "name";
        public static final String COLUMN_CLIENT_RTN = "rtn";
        public static final String COLUMN_CLIENT_ACTIVE = "active";
        public static final String COLUMN_CLIENT_SYNC = "clientSync";

        private static final String TEXT_TYPE = " TEXT";
        private static final String BOOLEAN_TYPE = " BOOLEAN";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_CLIENTS =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_CLIENT_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_RTN + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_CODE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_ACTIVE + BOOLEAN_TYPE + COMMA_SEP +
                        COLUMN_CLIENT_SYNC + BOOLEAN_TYPE + " )";

        public static final String SQL_DROP_CLIENTS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class feedProductCategory implements BaseColumns{

        public static final String TABLE_NAME = "categoryproduct";
        public static final String COLUMN_CATEGORY_ID = "categoryId";
        public static final String COLUMN_CATEGORY_NAME = "name";
        public static final String COLUMN_CATEGORY_DESCRIPTION = "description";

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

        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_ORDER_ID = "orderId";
        public static final String COLUMN_ORDER_DESCRIPTION = "description";
        public static final String COLUMN_ORDER_URGENT = "isUrgent";
        public static final String COLUMN_ORDER_REQUEST_ON = "requestOn";
        public static final String COLUMN_ORDER_CODE = "code";
        public static final String COLUMN_ORDER_CLIENT_ID = "clientId";
        public static final String COLUMN_ORDER_PRODUCT_ID = "productId";
        public static final String COLUMN_ORDER_EMPLOYEE_ID = "employeeId";
        public static final String COLUMN_ORDER_SERIE_ID = "serieId";
        public static final String COLUMN_ORDER_ORDER_TYPE = "orderTypetId";
        public static final String COLUMN_ORDER_SYNC = "orderSync";

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
                        COLUMN_ORDER_URGENT + BOOLEAN_TYPE + COMMA_SEP +
                        COLUMN_ORDER_SYNC + BOOLEAN_TYPE + " )";

        public static final String SQL_DROP_ORDER =
                "DROP IF EXISTS " + TABLE_NAME;

        public static final String QUERY_COMPLETE_ORDER =
                "SELECT "+ feedClient.COLUMN_CLIENT_NAME + COMMA_SEP +
                        feedOrder.COLUMN_ORDER_DESCRIPTION + COMMA_SEP +feedOrder.COLUMN_ORDER_SYNC +
                        " FROM " + feedClient.TABLE_NAME +" INNER JOIN " + feedOrder.TABLE_NAME +" ON "+
                        feedClient.TABLE_NAME +"."+_ID +" = " + feedOrder.TABLE_NAME+"."+_ID+ " AND " +
                        feedOrder.COLUMN_ORDER_SYNC + " = 1";

        public static final String QUERY_PENDING_ORDER =
                "SELECT "+ feedClient.COLUMN_CLIENT_NAME + COMMA_SEP +
                        feedOrder.COLUMN_ORDER_DESCRIPTION + COMMA_SEP + feedOrder.COLUMN_ORDER_SYNC +
                        " FROM " + feedClient.TABLE_NAME +" INNER JOIN " + feedOrder.TABLE_NAME +" ON "+
                        feedClient.TABLE_NAME +"."+_ID +" = " + feedOrder.TABLE_NAME+"."+_ID+ " AND " +
                        feedOrder.COLUMN_ORDER_SYNC + " = 0";

    }

}
