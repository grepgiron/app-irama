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
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import irama.irama.Adapters.HolderAdapter;
import irama.irama.Models.parameters;
import irama.irama.R;
import irama.irama.Sqlite.DBHelper;
import irama.irama.Sqlite.Tables.feedSqlite;

/**
 * Created by enagi on 26/6/2017.
 */

public class list_complete extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private ArrayList<parameters> arrayOfOrders;
    private CheckBox checkBox;
    private parameters parameters;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View vw = inflater.inflate(R.layout.fragment_listcomplete, container, false);

        initComponents(vw);

        new Thread(new Runnable() {

            @Override
            public void run() {
                list_complete.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            db = dbHelper.getWritableDatabase();
                            Cursor c = db.rawQuery(feedSqlite.feedOrder.QUERY_COMPLETE_ORDER, null);
                            if (c != null) {
                                if (c.moveToFirst()) {
                                    do {
                                        if(c.getInt(2) == 1) {
                                            parameters = new parameters(c.getString(0).toString(),
                                                    c.getString(1).toString(),"  "," ", c.getInt(2));
                                            arrayOfOrders.add(parameters);
                                        }
                                    } while (c.moveToNext());
                                }
                            }
                            Log.e(getClass().getName(), "getOrders");

                        } catch (SQLiteException e) {
                            Log.e(getClass().getSimpleName(), "error getOrders: " + e);
                        } finally {
                            if (db != null) {
                                db.close();
                            }
                        }
                        listView.setHasFixedSize(true);
                        listView.setLayoutManager(layoutManager);
                        recyclerAdapter = new HolderAdapter(arrayOfOrders, vw.getContext());
                        listView.setAdapter(recyclerAdapter);
                    }
                });


            }
        }).start();

        return vw;
    }

    private void initComponents(View view) {
        try {

            dbHelper = new DBHelper(view.getContext());
            arrayOfOrders = new ArrayList<parameters>();
            listView = (RecyclerView) view.findViewById(R.id.complete_recyclerView);
            checkBox = (CheckBox) view.findViewById(R.id.check_state);
            layoutManager = new LinearLayoutManager(view.getContext());

            Log.e(getClass().getName(), "initComponents");

        }catch (Exception e){
            Log.e(getClass().getName(), "error initComponents: " + e);
        }
    }
}