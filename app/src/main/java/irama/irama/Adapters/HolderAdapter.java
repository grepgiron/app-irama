package irama.irama.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import irama.irama.Models.order;
import irama.irama.R;

import static android.content.ContentValues.TAG;

/**
 * Created by enagi on 1/7/2017.
 */

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ViewHolder> {

    private ArrayList<order> orders;
    private Context context;

    public HolderAdapter(ArrayList<order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public HolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, null);
        HolderAdapter.ViewHolder viewHolder = new HolderAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HolderAdapter.ViewHolder holder, int position) {

        final order order = orders.get(position);

        holder.tvClient.setText(order.getDescription());
        holder.tvDescription.setText(order.getRequestOn());

        if(order.getIsSync() == 1){
            holder.checkBox.setChecked(true);
            holder.checkBox.setClickable(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("chechked", order.getDescription());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != orders ? orders.size() : 0);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView tvClient;
        protected TextView tvDescription;
        protected CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.tvClient = (TextView)itemView.findViewById(R.id.client_item_list);
            this.tvDescription = (TextView)itemView.findViewById(R.id.description_item_list);
            this.checkBox = (CheckBox)itemView.findViewById(R.id.check_state);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick " + getPosition() + " " + orders.get(getPosition()).getDescription());
        }
    }
}
