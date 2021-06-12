package ridaz.wssalmaak.ui.selectcar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.carSelectAdapter;
import ridaz.wssalmaak.databinding.FragmentSelectCarBinding;
import ridaz.wssalmaak.models.Car;
import ridaz.wssalmaak.ui.updateprofile.cars.CreateNewCarActivity;

public class SelectCarFragment extends Fragment {

    private static final String TAG = "SelectCarFragment";
    private FragmentSelectCarBinding binding;

    private String villeDepart = "";
    private String LatitudeMapVilleDepart = "";
    private String LongitudeMapVilleDepart = "";

    private String adresseDepart = "";
    private String LatitudeMapAdresseDepart = "";
    private String LongitudeMapAdresseDepart = "";

    private String villeArrive = "";
    private String LatitudeMapVilleArrive = "";
    private String LongitudeMapVilleArrive = "";

    private String dateAndHeureDepart, dateAndHeureArrive, villesStope, nombreDesKilos, priceParKilo;

    private Integer SelectedCarId;


    private carSelectAdapter carSelectAdapter;
    private ArrayList<Car> carArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_car, container, false);
        goBack();



        villeDepart = getArguments().getString("villeDepart");
        LatitudeMapVilleDepart = getArguments().getString("LatitudeMapVilleDepart");
        LongitudeMapVilleDepart = getArguments().getString("LongitudeMapVilleDepart");

        adresseDepart = getArguments().getString("adresseDepart");
        LatitudeMapAdresseDepart = getArguments().getString("LatitudeMapAdresseDepart");
        LongitudeMapAdresseDepart = getArguments().getString("LongitudeMapAdresseDepart");

        villeArrive = getArguments().getString("villeArrive");
        LatitudeMapVilleArrive = getArguments().getString("LatitudeMapVilleArrive");
        LongitudeMapVilleArrive = getArguments().getString("LongitudeMapVilleArrive");

        dateAndHeureDepart = getArguments().getString("dateAndHeureDepart");
        dateAndHeureArrive = getArguments().getString("dateAndHeureArrive");
        villesStope = getArguments().getString("villesStope");
        nombreDesKilos = getArguments().getString("nombreDesKilos");
        priceParKilo = getArguments().getString("priceParKilo");




        init();

        Suivant();

        addNewCar();

        return binding.getRoot();
    }

    private void addNewCar() {
        binding.AjouterunenouvellevoitureBtnInSelectCarFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNewCarActivity.class);
                startActivity(intent);
            }
        });
    }


    private void Suivant() {
        binding.SuivantBtnInSelectCarFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("villeDepart",villeDepart);
                bundle.putString("LatitudeMapVilleDepart", LatitudeMapVilleDepart);
                bundle.putString("LongitudeMapVilleDepart", LongitudeMapVilleDepart);

                bundle.putString("adresseDepart",adresseDepart);
                bundle.putString("LatitudeMapAdresseDepart", LatitudeMapAdresseDepart);
                bundle.putString("LongitudeMapAdresseDepart", LongitudeMapAdresseDepart);

                bundle.putString("villeArrive",villeArrive);
                bundle.putString("LatitudeMapVilleArrive", LatitudeMapVilleArrive);
                bundle.putString("LongitudeMapVilleArrive", LongitudeMapVilleArrive);


                bundle.putString("dateAndHeureDepart",dateAndHeureDepart);
                bundle.putString("dateAndHeureArrive",dateAndHeureArrive);
                bundle.putString("villesStope",villesStope);
                bundle.putString("nombreDesKilos",nombreDesKilos);
                bundle.putString("priceParKilo",priceParKilo);
                bundle.putInt("SelectedCarId",SelectedCarId);

                Navigation.findNavController(view).navigate(R.id.action_selectCarFragment_to_paymentTypeFragment,bundle);
            }
        });
    }

    private void init() {
        carArrayList = new ArrayList<>();
        carArrayList.add(new Car(1, "1234", "C", "2", "DACIA DOCKER", "Voiture UE", "12/03/23", "-6750054"));
        carArrayList.add(new Car(2, "1234", "", "", "ALFA-ROMEO", "Voiture W", "12/03/23", "-16711680"));
        carArrayList.add(new Car(3, "1234", "", "", "DACIA DOCKER", "Voiture W", "12/03/24", "-39219"));
        carArrayList.add(new Car(4, "1234", "", "", "DACIA DOCKER", "Voiture W", "12/03/24", "-39219"));

        carSelectAdapter = new carSelectAdapter(getContext(), carArrayList, binding.CarListToSelectInSelectCarFrag);
        binding.CarListToSelectInSelectCarFrag.setAdapter(carSelectAdapter);
        binding.CarListToSelectInSelectCarFrag.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        SelectedCarId = carArrayList.get(0).getCarId();

        carSelectAdapter.setOnItemClickListener(new ridaz.wssalmaak.adapters.carSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View itemView) {
                ImageView im = itemView.findViewById(R.id.SelectIconInCarItem);
                Drawable icSelected = im.getDrawable();
                Drawable icNoV = getActivity().getResources().getDrawable(R.drawable.ic_valide);

              //  Toast.makeText(getContext(), "" + pos, Toast.LENGTH_SHORT).show();


                if (icSelected.getConstantState().equals(icNoV.getConstantState())) {
                    //     Toast.makeText(context, "non", Toast.LENGTH_SHORT).show();
                   // im.setImageResource(R.drawable.ic_valide_gray);
                    //im.setVisibility(View.INVISIBLE);


                } else {
                    //   Toast.makeText(context, "oui", Toast.LENGTH_SHORT).show();
                    im.setImageResource(R.drawable.ic_valide);
                    im.setVisibility(View.VISIBLE);

                    SelectedCarId = carArrayList.get(pos).getCarId();


                    for (int i = 0; i < carArrayList.size(); i++) {

                        if (i != pos) {
                            ImageView ic = carSelectAdapter.getItemByIndex(i).findViewById(R.id.SelectIconInCarItem);
                            ic.setImageResource(R.drawable.ic_valide_gray);
                            ic.setVisibility(View.INVISIBLE);


                        }

                    }


                }
            }
        });

    }


    private void goBack() {
        binding.GoBackIconInSelectCarFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();

            }
        });
    }
}