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

import irama.irama.Models.order;
import irama.irama.Models.product;

/**
 * Created by enagi on 3/8/2017.
 */

public class controllers_sqlite {

    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private ContentValues values;

    public controllers_sqlite(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
    }


    //Controllers SQLite CLIENTS

    public long insertClient(){
        long id = -1;

        return id;
    }


    public void updatedClient(String _id, String rtn, String code){
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

    //Controllers SQLite PRODUCTS

    public long insertProduct(product product){
        long id = -1;
        try {
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_NAME, product.getName());
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
            values.put(feedSqlite.feedProduct.COLUMN_PRODUCT_UNIT, product.getUnit());
            id = db.insert(feedSqlite.feedProduct.TABLE_NAME, null, values);
            return id;
        } catch (SQLiteException e) {
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
        return id;
    }

    public void updateProduct(String id){try {
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

    //Controllers SQLite ORDERS

    public long insertOrder(order order, String clientId, String employeeId, String productId){
        long id = -1;

        try{

            values.put(feedSqlite.feedOrder.COLUMN_ORDER_DESCRIPTION, order.getDescription());
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_REQUEST_ON, order.getRequestOn());
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_ORDER_TYPE, order.getOrderTypetId());
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_CLIENT_ID, clientId);
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_PRODUCT_ID, productId);
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_EMPLOYEE_ID, employeeId);
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_URGENT, order.getIsUrgent());

            id = db.insert(feedSqlite.feedOrder.TABLE_NAME, null, values);

            Log.d(getClass().getName(), "insertOrder: " + values.get(feedSqlite.feedOrder.COLUMN_ORDER_DESCRIPTION));
            return id;
        }catch (SQLiteException e){
            Log.e(getClass().getSimpleName(), "Error: "+ e);
        }finally {
            if (db != null){
                db.close();
            }
        }
        return id;
    }

    public void updateOrder(String clientId, String employeeId, String productId, String orderId){

        try {

            values.put(feedSqlite.feedOrder.COLUMN_ORDER_CLIENT_ID, clientId);
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_EMPLOYEE_ID, employeeId);
            values.put(feedSqlite.feedOrder.COLUMN_ORDER_PRODUCT_ID, productId);

            db.update(feedSqlite.feedOrder.TABLE_NAME, values, feedSqlite.feedOrder._ID + "=" + orderId, null );

            Log.d(getClass().getSimpleName(), "updateOrder");

        } catch (SQLiteException e) {
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
    }


}
