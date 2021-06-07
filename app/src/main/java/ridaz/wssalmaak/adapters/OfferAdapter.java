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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.models.Offer;
import ridaz.wssalmaak.models.StopeVille;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Offer> model_list;

    private ArrayList<Offer> model_listFull;


    private OnItemClickListener mListener;


    public OfferAdapter(Context context, ArrayList<Offer> model_list) {
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
        view = inflater.inflate(R.layout.offer_item, parent, false);

        return new MyViewHolder(view, mListener);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        if (model_list.get(position).getLivreur().getProfilePhotoUrl() == null){

            Glide.with(context).load(context.getDrawable(R.drawable.ridaz)).into(holder.userImage);

        }
        else {
            Glide.with(context).load(model_list.get(position).getLivreur().getProfilePhotoUrl()).into(holder.userImage);

        }
        if (model_list.get(position).getLivreur().getFirstName() == null){
            holder.userNam.setText("Rida");

        }
        else {
            holder.userNam.setText(model_list.get(position).getLivreur().getFirstName());

        }

        if (model_list.get(position).getPaymentType() == 0){
            holder.IconRsDirect.setVisibility(View.GONE);
        }
        else {
            holder.IconRsDirect.setVisibility(View.VISIBLE);

        }

        holder.userRating.setText("5/5 - 2Avis");
        holder.numberOfKilos.setText(String.valueOf(model_list.get(position).getNumberKilos()));
        holder.pricePerKilos.setText(String.valueOf(model_list.get(position).getPrixParKilos()));
        holder.villeDepart.setText(model_list.get(position).getVilleDepart());
        holder.villeArriver.setText(model_list.get(position).getVilleArriver());
        holder.dateDepart.setText(model_list.get(position).getDateDepart());
        holder.dateArriver.setText(model_list.get(position).getDateArriver());

        String villeStope = model_list.get(position).getStopeVilles();
        if (villeStope == ""){
            holder.ligne.setVisibility(View.GONE);
            holder.ImageStope.setVisibility(View.GONE);
            holder.ListVilleStope.setVisibility(View.GONE);

        }else {
            holder.ligne.setVisibility(View.VISIBLE);
            holder.ImageStope.setVisibility(View.VISIBLE);
            holder.ListVilleStope.setVisibility(View.VISIBLE);
            String[] stopeVilleAList = villeStope.split(";");

            ArrayList<StopeVille> arrayList = new ArrayList<>();
            for (String s: stopeVilleAList) {
                arrayList.add(new StopeVille(s));
            }

            StopeVilleAdapter stopeVilleAdapter = new StopeVilleAdapter(context,arrayList);
            holder.ListVilleStope.setAdapter(stopeVilleAdapter);
            holder.ListVilleStope.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));

        }



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

        CircleImageView userImage;
        TextView userNam,userRating,numberOfKilos,pricePerKilos,villeDepart,villeArriver,dateDepart,dateArriver;
        RecyclerView ListVilleStope;
        ImageView ImageStope,IconRsDirect;
        View ligne;


        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            userImage = itemView.findViewById(R.id.UserMvInOfferItem);
            userNam = itemView.findViewById(R.id.UserNameTvInOfferItem);
            userRating = itemView.findViewById(R.id.UserRatingNumberTvInOfferItem);
            numberOfKilos = itemView.findViewById(R.id.NumberKiloAndPrisTvInOfferItem);
            pricePerKilos = itemView.findViewById(R.id.PrisTvInOfferItem);
            villeDepart = itemView.findViewById(R.id.VilleDepartTvInOfferItem);
            villeArriver = itemView.findViewById(R.id.VilleArriverTvInOfferItem);
            dateDepart= itemView.findViewById(R.id.DateDepartTvInOfferItem);
            dateArriver = itemView.findViewById(R.id.DateArriverTvInOfferItem);
            ListVilleStope = itemView.findViewById(R.id.ListVilleStopeInOfferItem);
            ImageStope = itemView.findViewById(R.id.StopIconInOfferItem);
            IconRsDirect = itemView.findViewById(R.id.directRsIconInOfferItem);
            ligne = itemView.findViewById(R.id.view2);


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
            List<Offer> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(model_listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Offer item : model_listFull) {
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

