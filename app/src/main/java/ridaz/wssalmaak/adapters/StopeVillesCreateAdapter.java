package ridaz.wssalmaak.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ridaz.wssalmaak.R;

public class StopeVillesCreateAdapter extends RecyclerView.Adapter<StopeVillesCreateAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<String> model_list;

    private ArrayList<String> model_listFull;


    private OnItemClickListener mListener;


    public StopeVillesCreateAdapter(Context context, ArrayList<String> model_list) {
        this.context = context;
        this.model_list = model_list;
        this.model_listFull = new ArrayList<>(model_list);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.stope_villes_item_creat, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.villeStopetv.setText((position + 1) +". "+model_list.get(position));

       holder.deletIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model_list.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getItemCount()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount() {
        return model_list.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************class MyViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView villeStopetv;
        ImageView deletIc;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            villeStopetv = itemView.findViewById(R.id.villeStopeTvInSVIC);
            deletIc = itemView.findViewById(R.id.deleteIconInSVIC);



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
            List<String> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(model_listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String item : model_listFull) {
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

