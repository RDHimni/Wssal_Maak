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
import ridaz.wssalmaak.models.CarModel;

public class CarsModelAdapter extends RecyclerView.Adapter<CarsModelAdapter.MyViewHolder> implements Filterable {

    private static final String TAG = "CarsModelAdapter";

    private Context context;
    private ArrayList<CarModel> model_list;

    private ArrayList<CarModel> model_listFull = new ArrayList<>();


    private OnItemClickListener mListener;


    public CarsModelAdapter(Context context, ArrayList<CarModel> model_list) {
        this.context = context;
        this.model_list = model_list;
        this.model_listFull.addAll(model_list);

        Log.d(TAG, "rida CarsModelAdapter: " + model_listFull.toString());
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cars_model_item, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.carmodeltv.setText(model_list.get(position).getCarModel());

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

        TextView carmodeltv ;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            carmodeltv = itemView.findViewById(R.id.carsModelTv);

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

    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d(TAG, "rida performFiltering1:" + constraint);
            List<CarModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Log.d(TAG, "rida performFiltering2:" + constraint);
                filteredList.addAll(model_listFull);
            } else {
                Log.d(TAG, "rida performFiltering3:" + constraint);
                String filterPattern = constraint.toString().toLowerCase().trim();
                Log.d(TAG, "rida performFiltering4:" + filterPattern);
                for (CarModel item : model_listFull) {
                    Log.d(TAG, "rida performFiltering5:" + item.getCarModel());

                    if (item.getCarModel().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                       // Toast.makeText(context, ""+item.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "rida performFiltering6:" + item.getCarModel());

                    }

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

