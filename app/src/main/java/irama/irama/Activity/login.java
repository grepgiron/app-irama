package irama.irama.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.feedSqlite;

/**
 * Created by grego on 2/7/2017.
 */

public class login extends AppCompatActivity {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private TextView tvUser;
    private TextView tvPass;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void initComponents(){
        try {
            dbHelper = new DBHelper(login.this);
            db = dbHelper.getWritableDatabase();
            tvUser = (TextView) findViewById(R.id.eT_user);
            tvPass = (TextView) findViewById(R.id.eT_pass);
            btLogin = (Button) findViewById(R.id.login_button);
        }catch (Exception e ){
            Log.e(getClass().getName(), "error initComponents: " + e);
        }
    }

    private void login(){
        try {
            Cursor c = db.rawQuery(feedSqlite.feedUser.SQL_LOGIN, new String[]{tvUser.getText().toString(), tvPass.getText().toString()});

            if (c.getCount() > 0) {

                Log.d("cursor", String.valueOf(c.getCount()));
                Intent intent = new Intent(login.this, splash_screen.class);
                startActivity(intent);
            }
        }catch (Exception e ){
            Log.e(getClass().getName(), "error login: " + e);
        }
    }
}