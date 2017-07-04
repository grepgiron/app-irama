package irama.irama.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import irama.irama.Sqlite.Tables.feedSqlite;

/**
 * Created by enagi on 28/6/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_irama.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(feedSqlite.feedClient.SQL_CREATE_CLIENTS);
        db.execSQL(feedSqlite.feedProductCategory.SQL_CREATE_CATEGORY);
        db.execSQL(feedSqlite.feedOrder.SQL_CREATE_ORDER);
        db.execSQL(feedSqlite.feedUser.SQL_CREATE_USERS);
        db.execSQL(feedSqlite.feedOrderType.SQL_CREATE_ORDERTYPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(feedSqlite.feedClient.SQL_DROP_CLIENTS);
        db.execSQL(feedSqlite.feedProductCategory.SQL_DROP_CATEGORY);
        db.execSQL(feedSqlite.feedOrder.SQL_DROP_ORDER);
        db.execSQL(feedSqlite.feedUser.SQL_DROP_USERS);
        db.execSQL(feedSqlite.feedOrderType.SQL_DROP_ORDERTYPE);
        onCreate(db);
    }
}
