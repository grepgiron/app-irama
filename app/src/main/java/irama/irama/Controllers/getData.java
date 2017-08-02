package irama.irama.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import irama.irama.Models.clients;
import irama.irama.Models.order;
import irama.irama.Models.order_type;
import irama.irama.Models.parameters;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.feedSqlite;

/**
 * Created by grego on 23/7/2017.
 */

public class getData {

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    private ArrayList<order> arrayOfOders;
    private ArrayList<clients> arrayOfClients;
    private ArrayList<parameters> arrayOfParameters;
    private ArrayList<order_type> arrayOfOrderType;

    private clients client;
    private parameters parameter;



    public getData(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        arrayOfClients = new ArrayList<clients>();
        arrayOfParameters = new ArrayList<parameters>();
    }

    public ArrayList<clients> getClients(){
        try {
            Thread.sleep(200);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor c = sqLiteDatabase.rawQuery(feedSqlite.feedClient.QUERY_CLIENTS, null);
            if( c != null){
                if(c.moveToFirst()){
                    do{
                        client = new clients(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
                        arrayOfClients.add(client);
                    }while (c.moveToNext());
                }
            }
            Log.e(getClass().getName(), "getAdapter");
        }catch (SQLiteException e){
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
        }
        return arrayOfClients;
    }

    public ArrayList<parameters> getOrdersComplete(){

        try {
            sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor c = sqLiteDatabase.rawQuery(feedSqlite.feedOrder.QUERY_COMPLETE_ORDER, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        if(c.getInt(2) == 1) {
                            parameter = new parameters(c.getString(0).toString(),
                                    c.getString(1).toString(),"  "," ", c.getInt(2));
                            arrayOfParameters.add(parameter);
                        }
                    } while (c.moveToNext());
                }
            }
            Log.e(getClass().getName(), "getOrders");

        } catch (SQLiteException e) {
            Log.e(getClass().getSimpleName(), "error getOrders: " + e);
        } finally {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
        return arrayOfParameters;
    }

}
