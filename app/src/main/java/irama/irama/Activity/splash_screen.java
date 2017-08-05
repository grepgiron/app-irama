package irama.irama.Activity;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import irama.irama.Sqlite.getData;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;

/**
 * Created by enagi on 5/7/2017.
 */

public class splash_screen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;
    private Animation animation;
    private TextView textView;
    private ObjectAnimator animator;
    private Boolean isFinish;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private ContentValues values;
    private SQLiteDatabase sqLiteDatabase;
    private Animation in;
    private getData getData;
    private String json= "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initComponents();
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        /*textView.setAnimation(animation);
        animator.setDuration(4000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createDB();
                getData();
            }
        },5000);*/
    }

    private void initComponents(){

        try {
            dbHelper = new DBHelper(splash_screen.this);
            values = new ContentValues();

           /* progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            textView = (TextView) findViewById(R.id.text2a);

            animation = AnimationUtils.loadAnimation(splash_screen.this, R.anim.anim_download);
            in = AnimationUtils.loadAnimation(splash_screen.this, R.anim.open_animation);
            animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);

            Log.e(getClass().getName(), "initComponents");*/

        }catch (Exception e){
            Log.e(getClass().getName(), "error: " +e);
        }
    }

    private void getData(){
        Log.e(getClass().getName(), "getData");
        new getClients().execute();

    }

    private class getClients extends AsyncTask<String, String, JSONObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView.setAnimation(in);
            textView.setText("Obteniendo Clients...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

        }

    }

    private void createDB(){
        if(sharedPreferences.getBoolean("database_created", true)){
            Log.e(getClass().getName(), "createDatabase");
            try {
                dbHelper.getWritableDatabase();
                Log.e("Database", "created");
                final ProgressDialog progress = new ProgressDialog(this);
                progress.setTitle("Database");
                progress.setMessage("Please wait while the database is created...");
                progress.show();

                Runnable progressRunnable = new Runnable() {
                    @Override
                    public void run() {
                        progress.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);


            }catch (SQLiteException e){
                Log.d("SQLite", e.toString());
            }
            sharedPreferences.edit().putBoolean("database_created", false).commit();
        }
    }

    /*private void requestClients(){

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray array = null;
                values = new ContentValues();

                try {
                    sqLiteDatabase = dbHelper.getWritableDatabase();

                    array = response.getJSONArray("content");
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

                        sqLiteDatabase.insert(feedSqlite.feedClient.TABLE_NAME, null, values);

                        *//*String id = client.getString("_id");
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
                        json += "_phone: " + phone + "\n\n";*//*
                    }

                }catch (SQLiteException e){
                    Log.e(getClass().getSimpleName(), e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e(getClass().getSimpleName(), json);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getClass().getSimpleName(), error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }*/
}
