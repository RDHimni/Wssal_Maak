package ridaz.wssalmaak.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;


import java.util.ArrayList;

import ridaz.wssalmaak.models.PlaceApi;

public class PlaceAutoSuggestAdapter extends ArrayAdapter implements Filterable {


    int resource;
    Context context;

    ArrayList<String> results;
    PlaceApi placeApi=new PlaceApi();

    String ville;

    public PlaceAutoSuggestAdapter(String ville, Context context, int resId){
        super(context,resId);
        this.context=context;
        this.resource=resId;
        this.ville = ville;

    }

    @Override
    public int getCount(){
        return results.size();
    }

    @Override
    public String getItem(int pos){
        return results.get(pos);
    }

    @Override
    public Filter getFilter(){
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint!=null){
                    results=placeApi.autoComplete(ville + constraint.toString());

                    filterResults.values=results;
                    filterResults.count=results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results!=null && results.count>0){
                    notifyDataSetChanged();
                }
                else{
                    notifyDataSetInvalidated();
                }

            }
        };
        return filter;
    }

}
