package irama.irama.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;

import irama.irama.Models.product;

/**
 * Created by enagi on 3/8/2017.
 */

public class controllers_sqlite {

    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public controllers_sqlite(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }



    public void updatedClient(String _id, String rtn, String code){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(feedSqlite.feedClient.COLUMN_CLIENT_ID, _id);
            values.put(feedSqlite.feedClient.COLUMN_CLIENT_CODE, code);
            values.put(feedSqlite.feedClient.COLUMN_CLIENT_SYNC, 1);
            Log.e(getClass().getSimpleName(), feedSqlite.feedClient.COLUMN_CLIENT_RTN + "="+ rtn);
            db.update(feedSqlite.feedClient.TABLE_NAME, values, feedSqlite.feedClient.COLUMN_CLIENT_RTN + "="+ rtn, null );
        } catch (SQLiteException e) {
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
    }

    public void insertProduct(product product){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_NAME, product.getName());
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_UNIT, product.getUnit());
            db.insert(feedSqlite.feedProduct.TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
    }

    public void updateProduct(String id){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_ID, id);
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_SYNC, 1);
            Cursor c = db.rawQuery(feedSqlite.feedProduct.LAST_RECORD,  null);
            db.update(feedSqlite.feedProduct.TABLE_NAME, values, feedSqlite.feedProduct._ID + "=" + c.getString(0), null );
        } catch (SQLiteException e) {
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
    }
}