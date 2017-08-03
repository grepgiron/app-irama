package irama.irama.Activity.Fragmets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;
import irama.irama.Controllers.AppController;
import irama.irama.Controllers.postData;
import irama.irama.Models.clients;
import irama.irama.Models.order;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.feedSqlite;
import irama.irama.Activity.new_client;
import irama.irama.Activity.new_order;

/**
 * Created by grego on 18/7/2017.
 */

public class home_assistance extends Fragment {

    private CircleButton newClient, newOrder, syncOrders, syncClients, getClients;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<clients> arrayOfClients;

    private postData postData;

    private ContentValues values;

    private String url = "https://irama.api.cuatrocubossoluciones.com/api/clients";

    public home_assistance() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_assistance, container, false);

        initComponents(view);

        newClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "new client");
                Intent intent = new Intent(getContext(), new_client.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "new order");
                Intent intent = new Intent(getContext(), new_order.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        syncClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData.postClients(getClientSQLite());
                Log.e(getClass().getName(), "sync clients");
            }
        });

        syncOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "sync order");
            }
        });

        getClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestClients();
                Log.e(getClass().getName(), "get clients");

            }
        });

        return view;
    }

    private void initComponents(View view){
        try {
            dbHelper = new DBHelper(view.getContext());
            newClient = (CircleButton) view.findViewById(R.id.assistance_client);
            newOrder = (CircleButton) view.findViewById(R.id.assistance_order);
            syncOrders = (CircleButton) view.findViewById(R.id.assistance_sync_orders);
            syncClients = (CircleButton) view.findViewById(R.id.assistance_sync_clients);
            getClients = (CircleButton) view.findViewById(R.id.assistance_get_clients);

            postData = new postData(view.getContext());
            arrayOfClients = new ArrayList<clients>();

            Log.e(getClass().getName(), "initComponents");
        }catch (Exception e){
            Log.e(getClass().getName(), "error: " + e);
        }
    }

    private Boolean syncOrders(){

        try{
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery(feedSqlite.feedOrder.QUERY_PENDING_ORDER, null);
            if (c != null){
                if(c.moveToFirst()){
                    do{
                        //add to arrayOfOrder:
                    }while (c.moveToNext());
                }
            }

        }catch (SQLiteException e){
            Log.e(getClass().getName(), "error: " + e);
        }


        return false;
    }

    private void requestClients(){

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray array = null;
                values = new ContentValues();

                try {
                    sqLiteDatabase = dbHelper.getWritableDatabase();
                    array = response.getJSONArray("content");

                    if(array != null){
                        for(int i = 0; i < array.length(); i++){
                            JSONObject client = (JSONObject)array.get(i);

                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_ID, client.getString("_id"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_NAME, client.getString("name"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_CODE, client.getString("code"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_RTN, client.getString("rtn"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_EMAIL, client.getString("email"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_DIRECTION, client.getString("address"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_PHONE, client.getString("phone"));
                            values.put(feedSqlite.feedClient.COLUMN_CLIENT_SYNC, 1);

                            sqLiteDatabase.insertWithOnConflict(feedSqlite.feedClient.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                        /*String id = client.getString("_id");
                        String code = client.getString("code");
                        String name = client.getString("name");
                        String rtn = client.getString("rtn");
                        String email = client.getString("email");
                        String address = client.getString("address");
                        String phone = client.getString("phone");

                        json += "_id: " + id + "\n";
                        json += "_code: " + code + "\n";
                        json += "_name: " + name + "\n";
                        json += "_rtn: " + rtn + "\n";
                        json += "_email: " + email + "\n";
                        json += "_address: " + address + "\n";
                        json += "_phone: " + phone + "\n\n";*/
                        }
                    }

                }catch (SQLiteException e){
                    Log.e(getClass().getSimpleName(), e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    if(sqLiteDatabase!=null) {
                        sqLiteDatabase.close();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getClass().getSimpleName(), error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    private ArrayList<clients> getClientSQLite(){

        try{
            sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor c = sqLiteDatabase.rawQuery(feedSqlite.feedClient.QUERY_CLIENTS, null);
            if(c != null){
                if(c.moveToFirst()){
                    do {
                        if (c.getInt(6) != 1){
                            arrayOfClients.add(new clients(c.getString(0), c.getString(1), c.getString(2),
                                    c.getString(3), c.getString(4), c.getString(5), c.getInt(6)));
                        }
                    }while (c.moveToNext());
                }
            }

            Log.e(getClass().getSimpleName(), "getClients().Sql");

        }catch (SQLiteException e){
            Log.e(getClass().getSimpleName(), "Error: " + e);
        }finally {
            if(sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
        }
        return arrayOfClients;
    }
}
