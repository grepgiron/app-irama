package irama.irama.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import irama.irama.Models.order;
import irama.irama.R;

/**
 * Created by enagi on 28/6/2017.
 */

public class OrdersAdapter extends ArrayAdapter<order> {

    public OrdersAdapter(Context context, ArrayList<order> orders){
        super(context, 0, orders);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        order order = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent , false);
        }

        TextView tvClient = (TextView) convertView.findViewById(R.id.client_item_list);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check_state);

        tvClient.setText(order.getDescription());
        checkBox.setChecked(true);

        return convertView;
    }
}