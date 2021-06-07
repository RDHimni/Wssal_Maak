package ridaz.wssalmaak.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.models.PlaceApi;

public class AdresseAdapter extends RecyclerView.Adapter<AdresseAdapter.MyViewHolder> implements Filterable {

    private static final String TAG = "AdresseAdapter";
    private Context context;

    ArrayList<String> results = new ArrayList<>();
    PlaceApi placeApi=new PlaceApi();


    private OnItemClickListener mListener;


    public AdresseAdapter(Context context) {
        this.context = context;

    }

    public ArrayList<String> getResults() {
        return results;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.adresse_item, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.adresseTv.setText(results.get(position));

    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getItemCount()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        return results.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************class MyViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView adresseTv;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            adresseTv = itemView.findViewById(R.id.adresseTv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, v);
                        }
                    }
                }
            });
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************interface OnItemClickListener()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************interface OnItemClickListener()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Filter getFilter(){
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint!=null){
                    results=placeApi.autoComplete(constraint.toString());

                    filterResults.values=results;
                    filterResults.count=results.size();

                    Log.d(TAG, "rida2 performFiltering: "+results);

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results!=null && results.count>0){
                    notifyDataSetChanged();
                }
                else{
                    //notifyDataSetInvalidated();

                    Toast.makeText(context, "Adresse invalide", Toast.LENGTH_SHORT).show();
                }

            }
        };
        return filter;
    }

}

