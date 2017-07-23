package irama.irama.Fragmets;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import at.markushi.ui.CircleButton;
import irama.irama.R;
import irama.irama.new_client;
import irama.irama.new_order;

/**
 * Created by grego on 18/7/2017.
 */

public class home_assistance extends Fragment {

    private CircleButton newclient, neworder, syncOrders, syncClients, getClients;


    public home_assistance() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_assistance, container, false);

        initComponents(view);

        newclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "new client");
                Intent intent = new Intent(getContext(), new_client.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        neworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "new order");
                Intent intent = new Intent(getContext(), new_order.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        syncClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "sync clients");
            }
        });

        syncOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "sync order");
            }
        });

        getClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getClass().getName(), "get clients");
            }
        });

        return view;
    }

    private void initComponents(View view){
        newclient = (CircleButton)view.findViewById(R.id.assistance_client);
        neworder = (CircleButton)view.findViewById(R.id.assistance_order);
        syncOrders = (CircleButton)view.findViewById(R.id.assistance_sync_orders);
        syncClients = (CircleButton)view.findViewById(R.id.assistance_sync_clients);
        getClients = (CircleButton)view.findViewById(R.id.assistance_get_clients);

    }
}
