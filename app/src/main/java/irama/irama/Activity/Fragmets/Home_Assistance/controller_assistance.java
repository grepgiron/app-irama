package irama.irama.Activity.Fragmets.Home_Assistance;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import irama.irama.Controllers.AppController;
import irama.irama.Controllers.Links;
import irama.irama.Controllers.postData;
import irama.irama.Models.clients;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.feedSqlite;

/**
 * Created by enagi on 3/8/2017.
 */

public class controller_assistance {

    private Context context;

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private ArrayList<clients> arrayOfClients;
    private ContentValues values;

    private String url = Links.clients;

    public controller_assistance(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        arrayOfClients = new ArrayList<clients>();
    }

    public void requestClients(){

        final ProgressDialog progressDialog = new ProgressDialog(this.context);
        progressDialog.setTitle("Updating Clients");
        progressDialog.setMessage("Please wait while the list clients is updated");
        progressDialog.show();

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
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

                                    Cursor c = sqLiteDatabase.rawQuery(feedSqlite.feedClient.EXIST_CLIENT, new String[]{client.getString("rtn")});
                                    if(!(c.getCount() > 0))
                                    {
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_ID, client.getString("_id"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_NAME, client.getString("name"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_CODE, client.getString("code"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_RTN, client.getString("rtn"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_EMAIL, client.getString("email"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_DIRECTION, client.getString("address"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_PHONE, client.getString("phone"));
                                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_SYNC, 1);

                                        sqLiteDatabase.insertWithOnConflict(feedSqlite.feedClient.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                                    }
                                }
                            }else{
                                Log.e(getClass().getSimpleName(), "Array empty");
                            }

                        }catch (SQLiteException e){
                            Log.e(getClass().getSimpleName(), e.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {
                            if(sqLiteDatabase!=null) {
                                sqLiteDatabase.close();
                            }
                            Toast.makeText(context, "List updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getClass().getSimpleName(), error.toString());
                    }
                });
                AppController.getInstance().addToRequestQueue(req);
                progressDialog.cancel();
            }
        };
        Handler handlerProgress = new Handler();
        handlerProgress.postDelayed(progressRunnable, 3000);
    }

    //(y)
    public void requestProductCategorie(){
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Links.categories, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray array = null;
                values = new ContentValues();
                try {
                    sqLiteDatabase = dbHelper.getWritableDatabase();
                    array = response.getJSONArray("content");
                    if(array != null){
                        for(int i = 0; i < array.length(); i++){
                            JSONObject category = (JSONObject)array.get(i);

                            Cursor c = sqLiteDatabase.rawQuery(feedSqlite.feedProductCategory.EXIST_CATEGORY, new String[]{category.getString("_id")});
                            if(!(c.getCount() > 0))
                            {
                                values.put(feedSqlite.feedProductCategory.COLUMN_CATEGORY_NAME, category.getString("name"));
                                values.put(feedSqlite.feedProductCategory.COLUMN_CATEGORY_DESCRIPTION, category.getString("description"));
                                values.put(feedSqlite.feedProductCategory.COLUMN_CATEGORY_ID, category.getString("_id"));

                                sqLiteDatabase.insertWithOnConflict(feedSqlite.feedProductCategory.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                            }
                        }
                    }else{
                        Log.e(getClass().getSimpleName(), "Array empty");
                    }

                }catch (SQLiteException e){
                    Log.e(getClass().getSimpleName(), e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    if(sqLiteDatabase!=null) {
                        sqLiteDatabase.close();
                    }
                    Toast.makeText(context, "List updated", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getClass().getSimpleName(), error.toString());
            }
        });

    }

}
