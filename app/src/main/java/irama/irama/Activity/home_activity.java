package irama.irama.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import irama.irama.Controllers.Adapters.ViewPagerAdapter;
import irama.irama.Activity.Fragmets.*;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;


/**
 * Created by grego on 18/7/2017.
 */

public class home_activity extends AppCompatActivity{

    private BottomNavigationView bottomNavigationView;
    private list_complete listComplete;
    private list_pending listPending;
    private home_assistance homeAssistance;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private SharedPreferences sharedPreferences;
    private DBHelper dbHelper;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        viewPager = (ViewPager) findViewById(R.id.view_pager_home);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_assistance:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_pending:
                        viewPager.setCurrentItem(1);
                        break;
                    case  R.id.action_complete:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                }
                return false;
            }
        });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuItem != null){
                    menuItem.setChecked(false);
                }else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                }
                Log.e(getClass().getName(), "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initViewPager(viewPager);

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
    }

    private void initViewPager(ViewPager viewPager) {
        try {
            dbHelper = new DBHelper(this);
            sharedPreferences = getSharedPreferences("FirstTime",0);

            listComplete = new list_complete();
            listPending = new list_pending();
            homeAssistance = new home_assistance();

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPagerAdapter.addFragment(homeAssistance);
            viewPagerAdapter.addFragment(listPending);
            viewPagerAdapter.addFragment(listComplete);
            viewPager.setAdapter(viewPagerAdapter);

            Log.e(getClass().getName(), "initViewPager");
        }catch (Exception e){
            Log.e(getClass().getName(), "error initViewPager: " + e);
        }
    }

}
