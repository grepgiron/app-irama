package irama.irama.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Pattern;

import irama.irama.Controllers.NetworkState;
import irama.irama.Controllers.postData;
import irama.irama.Models.clients;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.feedSqlite;

/**
 * Created by grego on 16/7/2017.
 */

public class new_client extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner_phone, spinner_email;
    private TextInputEditText name, rtn, phone, email, direction;
    private TextInputLayout nameLayout, rtnLayout, phoneLayout, emailLayout, directionLayout;

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues values;

    private NetworkState networkState;
    private postData postData;

    private clients client;

    String spinnerPhone[] = {"Mobile","Job"};
    String spinnerEmail[] = {"Personal","Job"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_client);
        setSupportActionBar(toolbar);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();
        initSpinner();

    }

    private void initComponents(){
        //
        try {
            name = (TextInputEditText) findViewById(R.id.nc_name);
            rtn = (TextInputEditText) findViewById(R.id.nc_rtn);
            phone = (TextInputEditText) findViewById(R.id.nc_phone);
            email = (TextInputEditText) findViewById(R.id.nc_emails);
            direction = (TextInputEditText) findViewById(R.id.nc_direction);

            nameLayout = (TextInputLayout) findViewById(R.id.layout_name);
            rtnLayout = (TextInputLayout) findViewById(R.id.layout_rtn);
            phoneLayout = (TextInputLayout) findViewById(R.id.layout_phone);
            emailLayout = (TextInputLayout) findViewById(R.id.layout_email);
            directionLayout = (TextInputLayout) findViewById(R.id.layout_address);

            spinner_phone = (Spinner)findViewById(R.id.spinner_phone);
            spinner_email = (Spinner)findViewById(R.id.spinner_email);
            //
            dbHelper = new DBHelper(new_client.this);
            networkState = new NetworkState(this);
            postData = new postData(this);

            Log.e(getClass().getName(), "initComponents");

        }catch (Exception e){
            Log.e(getClass().getName(), "error initComponents: " + e);
        }
    }

    private void initSpinner(){
        try{

            ArrayAdapter<String> spinnerArrayAdapterPhone = new ArrayAdapter<String>(this,   android.R.layout.simple_list_item_1, spinnerPhone);
            spinnerArrayAdapterPhone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spinner_phone.setAdapter(spinnerArrayAdapterPhone);
            spinner_phone.setSelection(0);

            ArrayAdapter<String> spinnerArrayAdapterEmail = new ArrayAdapter<String>(this,   android.R.layout.simple_list_item_1, spinnerEmail);
            spinnerArrayAdapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spinner_email.setAdapter(spinnerArrayAdapterEmail);
            spinner_email.setSelection(0);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(getClass().getName(), "close");
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Log.d(getClass().getName(), "close");
        finish();
        super.onBackPressed();
    }

    private void insertClient(){

        try {

            if (!TextUtils.isEmpty(name.getText().toString()) || !TextUtils.isEmpty(rtn.getText().toString())) {
                try {
                    sqLiteDatabase = dbHelper.getWritableDatabase();

                    values = new ContentValues();
                    values.put(feedSqlite.feedClient.COLUMN_CLIENT_NAME, name.getText().toString());
                    values.put(feedSqlite.feedClient.COLUMN_CLIENT_RTN, rtn.getText().toString());
                    values.put(feedSqlite.feedClient.COLUMN_CLIENT_EMAIL, email.getText().toString());
                    values.put(feedSqlite.feedClient.COLUMN_CLIENT_DIRECTION, direction.getText().toString());
                    values.put(feedSqlite.feedClient.COLUMN_CLIENT_PHONE, phone.getText().toString());
                    sqLiteDatabase.insert(feedSqlite.feedClient.TABLE_NAME, null, values);


                } catch (SQLiteException e) {
                    Log.e(getClass().getName(), e.toString());
                } finally {
                    sqLiteDatabase.close();
                }

                Log.e(getClass().getName(), "save clicked ok");
                finish();

            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(new_client.this).create();
                alertDialog.setTitle("new client");
                alertDialog.setMessage("text field is empty");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                Log.e(getClass().getName(), "clicked empty");
            }
        }catch (Exception e){
            Log.e(getClass().getName(), "error insertClients: " + e);
        }
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
                validateData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()){
            case R.id.nc_save:
                insertClient();
                break;
            case R.id.nc_cancel:
                finish();
                break;
        }*/
    }

    private Boolean validateName(String names){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        if (!pattern.matcher(names).matches()){
            nameLayout.setError("Invalidate Name");
            return false;
        }else {
            nameLayout.setError(null);
        }
        return true;
    }

    private Boolean validatePhone(String phones){

        if (!Patterns.PHONE.matcher(phones).matches()){
            phoneLayout.setError("Invalidate Phone");
            return false;
        }else {
            phoneLayout.setError(null);
        }
        return true;
    }

    private Boolean validateEmail(String emails){

        if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            emailLayout.setError("Invalidate Email");
            return false;
        }else {
            emailLayout.setError(null);
        }
        return true;
    }


    private void validateData(){

        boolean a = validateName(name.getText().toString());
        boolean c = validatePhone(phone.getText().toString());
        boolean d = validateEmail(email.getText().toString());
        if (a && c && d){
            insertClient();
        }

    }

}
