package irama.irama.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import irama.irama.Models.order;
import irama.irama.Models.product;
import irama.irama.R;
import irama.irama.Sqlite.controllers_sqlite;

/**
 * Created by grego on 16/7/2017.
 */

public class detail_order extends AppCompatActivity {


    private controllers_sqlite controllersSqlite;

    private order order;
    private product product;


    private long producId, clientId, orderId;

    private TextInputEditText productName, productDescription, productUnit, productExempt;
    private TextInputLayout inputProductName, inputProductDescription, inputProductUnit, inputProductExempt;

    private Spinner productCategory;

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

        order = new order();
        product = new product();

        clientId = getIntent().getExtras().getLong("clientId");

        productName = (TextInputEditText)findViewById(R.id.name_product);
        productDescription = (TextInputEditText)findViewById(R.id.description_product);
        productUnit = (TextInputEditText)findViewById(R.id.unit_product);
        productExempt = (TextInputEditText)findViewById(R.id.exempt_product);

        productCategory = (Spinner)findViewById(R.id.spinner_category_product);

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


    public void newOrder(){
        if(validateData()) {
            producId = controllersSqlite.insertProduct(product);
            orderId = controllersSqlite.insertOrder(order, String.valueOf(clientId), "", String.valueOf(producId));
        }else {
            Log.d(getClass().getSimpleName(), "data empty");
        }
    }

    private Boolean validateData(){


        return false;
    }

}
