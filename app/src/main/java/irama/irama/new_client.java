package irama.irama;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import irama.irama.Models.clients;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

/**
 * Created by grego on 16/7/2017.
 */

public class new_client extends AppCompatActivity implements View.OnClickListener{

    private Button save;
    private Button cancel;
    private EditText name, rtn, phone, email, direction;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues values;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_client);
        setSupportActionBar(toolbar);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();


    }


    private void initComponents(){
        //
        save = (Button)findViewById(R.id.nc_save);
        cancel = (Button)findViewById(R.id.nc_cancel);
        name = (EditText)findViewById(R.id.nc_name);
        rtn = (EditText)findViewById(R.id.nc_rtn);
        phone = (EditText)findViewById(R.id.nc_phone);
        email = (EditText)findViewById(R.id.nc_emails);
        direction = (EditText)findViewById(R.id.nc_direction);
        //
        dbHelper = new DBHelper(new_client.this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void insertClient(){

                if(!TextUtils.isEmpty(name.getText().toString()) || !TextUtils.isEmpty(rtn.getText().toString())){
                    try {
                        sqLiteDatabase = dbHelper.getWritableDatabase();

                        values = new ContentValues();
                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_NAME, name.getText().toString());
                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_RTN, rtn.getText().toString());
                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_EMAIL, email.getText().toString());
                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_DIRECTION, direction.getText().toString());
                        values.put(feedSqlite.feedClient.COLUMN_CLIENT_PHONE, phone.getText().toString());

                        sqLiteDatabase.insert(feedSqlite.feedClient.TABLE_NAME, null, values);

                    }catch (SQLiteException e){
                        Log.e(getClass().getName(), e.toString());
                    }finally {
                        sqLiteDatabase.close();
                    }

                    Log.e(getClass().getName(), "save clicked ok");
                    finish();

                }else{
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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nc_save:
                insertClient();
                break;
            case R.id.nc_cancel:
                finish();
                break;
        }
    }
}
