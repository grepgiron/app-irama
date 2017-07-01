package irama.irama.Fragmets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import irama.irama.Adapters.OrdersAdapter;
import irama.irama.Models.order;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

/**
 * Created by enagi on 26/6/2017.
 */

public class list_pending extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private ArrayList<irama.irama.Models.order> arrayOfOrders;
    private CheckBox checkBox;
    private order order;
    private OrdersAdapter ordersAdapter;
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_listpending, container, false);
        initComponents(vw);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    db = dbHelper.getWritableDatabase();
                    Cursor c = db.rawQuery(feedSqlite.feedOrder.QUERY_PENDING_ORDER, null);
                    if(c != null){
                        if(c.moveToFirst()) {
                            do {
                                order = new order(c.getString(0).toString(),
                                        c.getString(1).toString(),c.getInt(2));
                                Log.e("List_Pending", "get values " + c.getInt(2));
                                Log.e("List_Pending", "Insert " + c.getString(0));
                                arrayOfOrders.add(order);
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
                ordersAdapter = new OrdersAdapter(getContext(), arrayOfOrders );
                listView.setAdapter(ordersAdapter);
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
        arrayOfOrders = new ArrayList<order>();
        listView = (ListView) view.findViewById(R.id.pending_listview);
        checkBox = (CheckBox) view.findViewById(R.id.check_state);
    }



}
