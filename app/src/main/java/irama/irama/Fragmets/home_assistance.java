package irama.irama.Fragmets;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.markushi.ui.CircleButton;
import irama.irama.R;
import irama.irama.new_client;
import irama.irama.new_order;

/**
 * Created by grego on 18/7/2017.
 */

public class home_assistance extends Fragment {

    private CircleButton newclient;
    private CircleButton neworder;

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
                Intent intent = new Intent(getContext(), new_client.class);
                startActivity(intent);
            }
        });

        neworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), new_order.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initComponents(View view){
        newclient = (CircleButton)view.findViewById(R.id.assistance_client);
        neworder = (CircleButton)view.findViewById(R.id.assistance_order);
    }
}
