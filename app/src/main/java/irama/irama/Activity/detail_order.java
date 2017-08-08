package irama.irama.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import irama.irama.Controllers.postData;
import irama.irama.Models.order;
import irama.irama.Models.product;
import irama.irama.R;
import irama.irama.Sqlite.controllers_sqlite;

/**
 * Created by grego on 16/7/2017.
 */

public class detail_order extends AppCompatActivity {


    private controllers_sqlite controllersSqlite;
    private postData postData;

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

    //Insert New Orders
    public void newOrder(){
        if(validateData()) {

            producId = controllersSqlite.insertProduct(product);
            orderId = controllersSqlite.insertOrder(order, String.valueOf(clientId), "", String.valueOf(producId));

            if(connectionAvailable()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this.getBaseContext());
                builder.setMessage("sync this").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //sync this order
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detail_order.this.finish();
                    }
                });
                builder.create();
                builder.show();
            }else {
                detail_order.this.finish();
            }
        }else {
            Log.d(getClass().getSimpleName(), "data empty");
        }
    }

    //Validate TextEdit
    private Boolean validateData(){

        boolean a = validateProductName();
        boolean b = validateProductDescription();
        boolean c = validateProductUnit();
        boolean d = validateProductExempt();

        if(a && b && c && d){
            newOrder();
            return true;
        }
        return false;
    }

    private Boolean validateProductName(){

        if(TextUtils.isEmpty(productName.getText().toString())){
            inputProductName.setError("No debe estar vacio");
            return false;
        }else {
            inputProductName.setError(null);
        }
        return true;
    }

    private Boolean validateProductDescription(){

        if(TextUtils.isEmpty(productDescription.getText().toString())){
            inputProductDescription.setError("No debe estar vacio");
            return false;
        }else {
            inputProductDescription.setError(null);
        }
        return true;
    }

    private Boolean validateProductUnit(){

        if(TextUtils.isEmpty(productUnit.getText().toString())){
            inputProductUnit.setError("No debe estar vacio");
            return false;
        }else {
            inputProductUnit.setError(null);
        }
        return true;
    }

    private Boolean validateProductExempt(){

        if(TextUtils.isEmpty(productExempt.getText().toString())){
            inputProductExempt.setError("No debe estar vacio");
            return false;
        }else {
            inputProductExempt.setError(null);
        }
        return true;
    }

    //Connection is available?

    private Boolean connectionAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED){
            return true;
        }
        return false;
    }

    //
    private void syncOrder(){



    }


}
