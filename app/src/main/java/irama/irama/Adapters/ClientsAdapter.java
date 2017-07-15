package irama.irama.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import irama.irama.Models.clients;
import irama.irama.R;

/**
 * Created by grego on 13/7/2017.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> implements Filterable {

    ArrayList<clients> clientses;
    Context context;
    CustomFilter filter;


    public ClientsAdapter(ArrayList<clients> clientses, Context context) {
        this.clientses = clientses;
        this.context = context;
    }

    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item_listview, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientsAdapter.ViewHolder holder, int position) {

        final clients clients = clientses.get(position);

        holder.tvClient.setText(clients.getName());
        holder.tvDescription.setText(clients.getRtn());

    }

    @Override
    public int getItemCount() {
        return (null != clientses ? clientses.size() : 0);
    }

    @Override
    public Filter getFilter() {
        if(filter != null){
            filter = new CustomFilter(this, clientses);
        }
        return filter;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvClient;
        protected TextView tvDescription;
        protected ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.tvClient = (TextView)itemView.findViewById(R.id.client_item_list);
            this.tvDescription = (TextView)itemView.findViewById(R.id.description_item_list);
            this.image = (ImageView)itemView.findViewById(R.id.client_image);

        }

        @Override
        public void onClick(View v) {
            Log.e(getClass().getName().toString(), clientses.get(this.getPosition()).getName());
        }
    }


}
