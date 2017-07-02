package irama.irama;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import irama.irama.Adapters.PageAdapter;
import irama.irama.Models.order;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

public class home extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private DBHelper dbHelper;
    private ArrayAdapter<String> arrayOfOrders;
    private SQLiteDatabase db;
    private Bundle bundle;
    private AlertDialog.Builder syncDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponents();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Pendiente"));
        tabLayout.addTab(tabLayout.newTab().setText("Completado"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, new_order.class);
                startActivity(intent, bundle);
            }
        });

        if(sharedPreferences.getBoolean("database_created", true)){

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncDialog.setIcon(R.drawable.ic_assignment_grey);
                syncDialog.setTitle("Sync Data");
                syncDialog.setPositiveButton("Sync", null);
                syncDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //mostrar
                catchData();
                syncDialog.setAdapter(arrayOfOrders, null);
            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sync, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sync_menu:
                syncDialog.show();
                break;

        }

        return true;
    }

    public void initComponents(){
        dbHelper = new DBHelper(home.this);
        sharedPreferences = getSharedPreferences("FirstTime",0);
        bundle = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.open_animation,R.anim.close_animation).toBundle();
        syncDialog = new AlertDialog.Builder(home.this);
        arrayOfOrders = new ArrayAdapter<String>(home.this, android.R.layout.simple_list_item_1);
    }

    public void catchData(){
        try{
            db = dbHelper.getWritableDatabase();
            Cursor c = db.rawQuery(feedSqlite.feedOrder.QUERY_PENDING_ORDER, null);
            if(c != null){
                if(c.moveToFirst()) {
                    do {
                        Log.e("List_Pending", "get values " + c.getInt(2));
                        Log.e("List_Pending", "Insert " + c.getString(0));
                        arrayOfOrders.add(c.getString(0).toString());
                    }while (c.moveToNext());
                }
            }
        }catch (SQLiteException e){
            Log.e(getClass().getSimpleName(), "Error database");
        }finally {
            if(db != null){
                db.close();
            }
        }

    }

}
