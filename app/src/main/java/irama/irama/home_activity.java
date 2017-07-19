package irama.irama;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import at.markushi.ui.CircleButton;
import irama.irama.Adapters.ViewPagerAdapter;
import irama.irama.Fragmets.*;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initComponents();

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
    }

    private void initComponents(){
    }



    private void initViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        listComplete = new list_complete();
        listPending = new list_pending();
        homeAssistance = new home_assistance();
        viewPagerAdapter.addFragment(homeAssistance);
        viewPagerAdapter.addFragment(listPending);
        viewPagerAdapter.addFragment(listComplete);
        viewPager.setAdapter(viewPagerAdapter);
    }

}
