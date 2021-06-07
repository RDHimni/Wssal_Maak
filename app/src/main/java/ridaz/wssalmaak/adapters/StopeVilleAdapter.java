package ridaz.wssalmaak.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.models.StopeVille;

public class StopeVilleAdapter extends RecyclerView.Adapter<StopeVilleAdapter.MyViewAdapter> implements Filterable {

    private Context context;
    private ArrayList<StopeVille> model_list;

    private ArrayList<StopeVille> model_listFull;


    private OnItemClickListener mListener;


    public StopeVilleAdapter(Context context, ArrayList<StopeVille> model_list) {
        this.context = context;
        this.model_list = model_list;
        this.model_listFull = new ArrayList<>(model_list);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.stopville_item, parent, false);

        return new MyViewAdapter(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, final int position) {

        holder.stopeVilleTv.setText(model_list.get(position).getVilleName());

    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getItemCount()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        return model_list.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************class MyViewAdapter()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public class MyViewAdapter extends RecyclerView.ViewHolder {

        TextView stopeVilleTv;


        public MyViewAdapter(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            stopeVilleTv = itemView.findViewById(R.id.StopVilleInStopVilleItem);


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
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StopeVille> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(model_listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (StopeVille item : model_listFull) {
                /*
                    if (item.getLabel().toLowerCase().contains(filterPattern) || item.getRef().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    */
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            model_list.clear();
            model_list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}

