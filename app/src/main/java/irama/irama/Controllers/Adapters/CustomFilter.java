package irama.irama.Controllers.Adapters;

import android.widget.Filter;

import java.util.ArrayList;

import irama.irama.Models.clients;

/**
 * Created by enagi on 14/7/2017.
 */

public class CustomFilter extends Filter {

    private ClientsAdapter clientsAdapter;
    private ArrayList<clients> clientsArrayList;

    public CustomFilter(ClientsAdapter clientsAdapter, ArrayList<clients> clientsArrayList) {
        this.clientsAdapter = clientsAdapter;
        this.clientsArrayList = clientsArrayList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if(constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<clients> filterClients = new ArrayList<>();
            for(int i=0; i < clientsArrayList.size(); i++){
                if (clientsArrayList.get(i).getName().toUpperCase().contains(constraint)){
                    filterClients.add(clientsArrayList.get(i));
                }
            }
            filterResults.count = filterClients.size();
            filterResults.values = filterClients;
        }else {
            filterResults.count = clientsArrayList.size();
            filterResults.values = clientsArrayList;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        clientsAdapter.clientses = (ArrayList<clients>) results.values;
        clientsAdapter.notifyDataSetChanged();
    }
}
