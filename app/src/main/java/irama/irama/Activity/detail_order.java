package irama.irama.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import irama.irama.Models.order;
import irama.irama.R;
import irama.irama.Sqlite.controllers_sqlite;

/**
 * Created by grego on 16/7/2017.
 */

public class detail_order extends AppCompatActivity {


    private controllers_sqlite controllersSqlite;
    private order order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initComponents();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initComponents(){
        controllersSqlite = new controllers_sqlite(this.getBaseContext());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d(getClass().getName(), "close");
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
