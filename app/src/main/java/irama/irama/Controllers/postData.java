package irama.irama.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import irama.irama.Models.clients;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.controllers_sqlite;
import irama.irama.Sqlite.feedSqlite;

/**
 * Created by grego on 1/8/2017.
 */

public class postData {

    private Context context;
    private int position;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private controllers_sqlite controller;

    public postData(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        this.position = 0;
        controller = new controllers_sqlite(context);
    }

    public void postClients(final ArrayList<clients> arrayClients){
        if(arrayClients != null){
            for(position = 0; position < arrayClients.size(); position++){
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(this.context);
                    String URL = Links.clients;
                    JSONObject jsonBody = new JSONObject();

                    jsonBody.put("name", arrayClients.get(position).getName());
                    jsonBody.put("rtn", arrayClients.get(position).getRtn());
                    jsonBody.put("email", arrayClients.get(position).getEmail());
                    jsonBody.put("address", arrayClients.get(position).getAddress());
                    jsonBody.put("phone", arrayClients.get(position).getPhone());

                    final String rtn = arrayClients.get(position).getRtn();

                    final String requestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            JsonObject jsonObject;
                            if (response != null) {

                                db = dbHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                responseString = String.valueOf(response.statusCode);
                                String string = "";
                                String _id, code;
                                try {

                                    string = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                                    jsonObject = new JsonParser().parse(string).getAsJsonObject();
                                    _id = jsonObject.get("_id").toString().replaceAll("\"", "");
                                    code = jsonObject.get("code").toString().replaceAll("\"", "");
                                    controller.updatedClient(_id, rtn, code);

                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }finally {
                                    if (db != null){
                                        db.close();
                                    }
                                }
                                Log.e("response post", string + " " + rtn);
                            }
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };
                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void postOrders(){

    }

}
