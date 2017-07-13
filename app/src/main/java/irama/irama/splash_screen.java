package irama.irama;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
                getData();
            }
        },5000);
    }

    private void initComponents(){

        dbHelper = new DBHelper(splash_screen.this);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        animation = AnimationUtils.loadAnimation(splash_screen.this, R.anim.anim_download);
        textView = (TextView)findViewById(R.id.text2a);
        in = AnimationUtils.loadAnimation(splash_screen.this, R.anim.open_animation);
        animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
    }

    private void getData(){
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


}
