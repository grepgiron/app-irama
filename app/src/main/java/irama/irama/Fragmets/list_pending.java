package irama.irama.Fragmets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import irama.irama.Adapters.HolderAdapter;
import irama.irama.Adapters.OrdersAdapter;
import irama.irama.Models.order;
import irama.irama.Models.parameters;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

/**
 * Created by enagi on 26/6/2017.
 */

public class list_pending extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private ArrayList<parameters> arrayOfOrders;
    private CheckBox checkBox;
    private parameters parameters;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private OrdersAdapter ordersAdapter;
    private RecyclerView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View vw = inflater.inflate(R.layout.fragment_listpending, container, false);
        initComponents(vw);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    db = dbHelper.getWritableDatabase();
                    Cursor c = db.rawQuery(feedSqlite.feedOrder.QUERY_PENDING_ORDER, null);
                    if(c != null){
                        Log.e("Cursor", "get values " + c.getCount());
                        if(c.moveToFirst()) {
                            do {
                                if(c.getInt(2) == 0) {
                                    parameters = new parameters(c.getString(0).toString(),
                                            c.getString(1).toString(),"","", c.getInt(2));
                                    Log.e("List_Pending", "get values " + c.getInt(2));
                                    Log.e("List_Pending", "Insert " + c.getString(0));
                                    arrayOfOrders.add(parameters);
                                }
                            }while (c.moveToNext());
                        }
                    }
                }catch (SQLiteException e){
                    Log.e(getClass().getSimpleName(), "Error database");
                }finally {
                    if(db != null){
                        db.close();
                    }
                }
                listView.setHasFixedSize(true);
                listView.setLayoutManager(layoutManager);
                recyclerAdapter = new HolderAdapter(arrayOfOrders, vw.getContext());
                listView.setAdapter(recyclerAdapter);
                //ordersAdapter = new OrdersAdapter(getContext(), arrayOfOrders );

            }
        }).start();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                Toast.makeText(v.getContext(), "presed", Toast.LENGTH_SHORT);
            }
        };


        return vw;
    }

    private void initComponents(View view){
        dbHelper = new DBHelper(view.getContext());
        arrayOfOrders = new ArrayList<parameters>();
        listView = (RecyclerView) view.findViewById(R.id.pending_recyclerView);
        checkBox = (CheckBox) view.findViewById(R.id.check_state);
        layoutManager = new LinearLayoutManager(view.getContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.check_state:
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            Toast.makeText(getContext(), "nuevo click", Toast.LENGTH_SHORT);
                        }
                    }
                });
                break;
        }
        return true;
    }
}
