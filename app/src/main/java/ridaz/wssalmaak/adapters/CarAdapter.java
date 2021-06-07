package ridaz.wssalmaak.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import ridaz.wssalmaak.models.Car;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Car> model_list;

    private ArrayList<Car> model_listFull;


    private OnItemClickListener mListener;


    public CarAdapter(Context context, ArrayList<Car> model_list) {
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
        view = inflater.inflate(R.layout.car_item, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.model.setText(model_list.get(position).getModel());
        holder.type.setText(model_list.get(position).getType());
        holder.matricule1.setText(model_list.get(position).getMatricule1());
        holder.matricule2.setText(model_list.get(position).getMatricule2());
        holder.matricule3.setText(model_list.get(position).getMatricule3());
        holder.dateExperatrionassBtn.setText(model_list.get(position).getDateExperationAssurance());
        holder.colorBtn.setCardBackgroundColor(Integer.parseInt(model_list.get(position).getColor()));

         if (model_list.get(position).getType().contains("W")){
             holder.matricule2.setFocusable(false);
             holder.matricule3.setFocusable(false);
             holder.matricule2.setBackground(context.getResources().getDrawable(R.drawable.button_bg_hided));
             holder.matricule3.setBackground(context.getResources().getDrawable(R.drawable.button_bg_hided));
         }
         else {
             holder.matricule2.setFocusable(true);
             holder.matricule2.setFocusableInTouchMode(true);
             holder.matricule3.setFocusable(true);
             holder.matricule3.setFocusableInTouchMode(true);

             holder.matricule2.setBackground(context.getResources().getDrawable(R.drawable.button_bg_slected));
             holder.matricule3.setBackground(context.getResources().getDrawable(R.drawable.button_bg_slected));
         }

         holder.delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 /**delete car*/
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

        TextView model,type;
        EditText matricule1,matricule2,matricule3;
        Button dateExperatrionassBtn;
        CardView colorBtn;
        ImageView delete;


        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            model = itemView.findViewById(R.id.modelCarTvInCarItem);
            type = itemView.findViewById(R.id.TypeCarTvInCarItem);
            matricule1 = itemView.findViewById(R.id.MatriculeCarEdittext1Id);
            matricule2 = itemView.findViewById(R.id.MatriculeCarEdittext2Id);
            matricule3 = itemView.findViewById(R.id.MatriculeCarEdittext3Id);
            dateExperatrionassBtn = itemView.findViewById(R.id.DateBtnInCarItem);
            colorBtn = itemView.findViewById(R.id.ColorBtnInCarItem);
            delete = itemView.findViewById(R.id.deleteIconInCarItem);

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

