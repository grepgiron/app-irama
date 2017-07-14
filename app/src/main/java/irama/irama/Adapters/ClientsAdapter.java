package irama.irama.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by grego on 13/7/2017.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ClientsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
