package ridaz.wssalmaak.ui.sizeandprice;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentSizeAndPriceBinding;


public class SizeAndPriceFragment extends Fragment {

    private static final String TAG = "SizeAndPriceFragment";
    private FragmentSizeAndPriceBinding binding;

    private String villeDepart = "";
    private String LatitudeMapVilleDepart = "";
    private String LongitudeMapVilleDepart = "";

    private String adresseDepart = "";
    private String LatitudeMapAdresseDepart = "";
    private String LongitudeMapAdresseDepart = "";

    private String villeArrive = "";
    private String LatitudeMapVilleArrive = "";
    private String LongitudeMapVilleArrive = "";

    private String dateAndHeureDepart,dateAndHeureArrive,villesStope,nombreDesKilos,priceParKilo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_size_and_price, container, false);
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


        Suivant();


        return binding.getRoot();
    }

    private void Suivant() {
        binding.SuivantBtnInSizeAndPriceFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombreDesKilos = binding.editTextNombredeskilosInSizeAndPriceFrag.getText().toString();
                priceParKilo = binding.editTextPricePerkilosInSizeAndPriceFrag.getText().toString();

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

                if (!nombreDesKilos.isEmpty()){
                    if (!priceParKilo.isEmpty()){

                        bundle.putString("nombreDesKilos",nombreDesKilos);
                        bundle.putString("priceParKilo",priceParKilo);

                        Navigation.findNavController(view).navigate(R.id.action_sizeAndPriceFragment_to_selectCarFragment,bundle);

                    }else {
                        Toast.makeText(getContext(), "saisissez le nombre des kilos !", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "saisissez le prix par kilo !", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void goBack() {
        binding.GoBackIconInSizeAndPriceFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();

            }
        });
    }
}