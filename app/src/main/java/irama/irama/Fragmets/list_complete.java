package irama.irama.Fragmets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import irama.irama.Adapters.OrdersAdapter;
import irama.irama.Models.order;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

/**
 * Created by enagi on 26/6/2017.
 */

public class list_complete extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private ArrayList<order> arrayOfOrders;
    private order order;
    private OrdersAdapter ordersAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_listcomplete, container, false);
        initComponents(vw);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    db = dbHelper.getWritableDatabase();
                    Cursor c = db.rawQuery(feedSqlite.feedOrder.QUERY_ALL_ORDER, null);
                    if(c != null){
                        if(c.moveToFirst()) {
                            do {
                                order = new order(c.getString(2).toString(),
                                        1);
                                Log.e(getClass().getSimpleName(), "Insert " + c.getString(2));
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
        return vw;

    }


    private void initComponents(View view){
        dbHelper = new DBHelper(view.getContext());
        arrayOfOrders = new ArrayList<order>();
        listView = (ListView) view.findViewById(R.id.complete_listview);
    }




}
