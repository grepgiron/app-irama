package irama.irama;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;

import irama.irama.Adapters.ClientsAdapter;
import irama.irama.Models.clients;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

public class new_order extends AppCompatActivity {


    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<clients> arrayOfClients;
    private clients clients;
    private ClientsAdapter clientsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        initComponents();
        layoutManager = new LinearLayoutManager(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            Thread.sleep(200);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor c = sqLiteDatabase.rawQuery(feedSqlite.feedClient.QUERY_CLIENTS, null);
            if( c != null){
                if(c.moveToFirst()){
                    do{
                        clients = new clients(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
                        arrayOfClients.add(clients);
                    }while (c.moveToNext());
                }
            }

        }catch (SQLiteException e){
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new ClientsAdapter(arrayOfClients, getBaseContext());
        recyclerView.setAdapter(recyclerAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    clientsAdapter.getFilter().filter(newText);

                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });


    }

    void initComponents(){
        dbHelper = new DBHelper(this);
        arrayOfClients = new ArrayList<clients>();
        recyclerView = (RecyclerView)findViewById(R.id.client_recycler);
        searchView = (SearchView)findViewById(R.id.search_client);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_new, menu);
        return true;
    }
}
