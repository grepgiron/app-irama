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
import irama.irama.Models.parameters;
import irama.irama.R;

/**
 * Created by enagi on 28/6/2017.
 */

public class OrdersAdapter extends ArrayAdapter<parameters> {

    public OrdersAdapter(Context context, ArrayList<parameters> parameterses){
        super(context, 0, parameterses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        parameters parameters = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent , false);
        }

        TextView tvClient = (TextView) convertView.findViewById(R.id.client_item_list);
        TextView tvDescription = (TextView)convertView.findViewById(R.id.description_item_list);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check_state);

        tvClient.setText(parameters.getName());
        tvDescription.setText(parameters.getDescription());

        if(parameters.getIsSync() == 1){
            checkBox.setChecked(true);
            checkBox.setClickable(false);
        }

        return convertView;
    }

}
