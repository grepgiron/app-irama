package irama.irama.Activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import irama.irama.Controllers.Adapters.ClientsAdapter;
import irama.irama.Controllers.getData;
import irama.irama.Models.clients;
import irama.irama.R;

public class new_order extends AppCompatActivity {


    private ArrayList<clients> arrayOfClients;
    private clients clients;
    private ClientsAdapter clientsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView recyclerView;
    private EditText searchView;

    private getData getData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        initComponents();
        layoutManager = new LinearLayoutManager(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_orders);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayOfClients = getData.getClients();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        clientsAdapter = new ClientsAdapter(arrayOfClients, getBaseContext());
        recyclerView.setAdapter(clientsAdapter);

        searchClient();
    }

    void initComponents(){
        getData = new getData(this);
        arrayOfClients = new ArrayList<clients>();
        recyclerView = (RecyclerView)findViewById(R.id.client_recycler);
        searchView = (EditText)findViewById(R.id.search_client);
        Log.e(getClass().getName(), "initComponents");

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

    private void searchClient() {

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s = s.toString().toLowerCase();
                final ArrayList<clients> filteredClients = new ArrayList<clients>();

                for (int i = 0; i < arrayOfClients.size(); i++) {
                    final clients client = arrayOfClients.get(i);
                    if (client.getRtn().contains(s)) {
                        filteredClients.add(arrayOfClients.get(i));
                    }
                }

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                clientsAdapter = new ClientsAdapter(filteredClients, getBaseContext());
                recyclerView.setAdapter(clientsAdapter);
                try {
                    recyclerAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.e(getClass().getName(), " Error" + e.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

}
