package irama.irama;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

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
    private Animation in;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initComponents();

        textView.setAnimation(animation);
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
        },5000);
    }

    private void initComponents(){

        dbHelper = new DBHelper(splash_screen.this);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        textView = (TextView)findViewById(R.id.text2a);

        animation = AnimationUtils.loadAnimation(splash_screen.this, R.anim.anim_download);
        in = AnimationUtils.loadAnimation(splash_screen.this, R.anim.open_animation);
        animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);

        Log.e(getClass().getName(), "initComponents");


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
}
