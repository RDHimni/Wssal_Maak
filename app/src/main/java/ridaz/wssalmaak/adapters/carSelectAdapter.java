package ridaz.wssalmaak.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.models.Car;

public class carSelectAdapter extends RecyclerView.Adapter<carSelectAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Car> model_list;

    private ArrayList<Car> model_listFull;

    private Integer SelectedCarId;

    private OnItemClickListener mListener;

    private RecyclerView carList;

    public carSelectAdapter(Context context, ArrayList<Car> model_list, RecyclerView carList) {
        this.context = context;
        this.model_list = model_list;
        this.model_listFull = new ArrayList<>(model_list);
        this.carList = carList;

    }


    public Integer getSelectedCarId() {
        return SelectedCarId;
    }

    public View getItemByIndex(int index) {
        return this.carList.getLayoutManager().findViewByPosition(index);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.car_to_select_item, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.Model.setText(model_list.get(position).getModel());
        holder.Type.setText(model_list.get(position).getType());

        if (position > 0) {

            ImageView ic = getItemByIndex(0).findViewById(R.id.SelectIconInCarItem);
            ic.setImageResource(R.drawable.ic_valide);
            ic.setVisibility(View.VISIBLE);
            this.SelectedCarId = model_list.get(0).getCarId();

        }

        if (position == model_list.size() - 1) holder.view.setVisibility(View.INVISIBLE);




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

        TextView Model, Type;
        ImageView SelectIc;

        View view;


        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            Model = itemView.findViewById(R.id.modelCarTvInCarSelectItem);
            Type = itemView.findViewById(R.id.TypeCarTvInCarSelectItem);
            SelectIc = itemView.findViewById(R.id.SelectIconInCarItem);
            view = itemView.findViewById(R.id.view);

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
            List<Car> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(model_listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Car item : model_listFull) {
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

