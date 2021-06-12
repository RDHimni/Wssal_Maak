package ridaz.wssalmaak.ui.paymenttype;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.text.DecimalFormat;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentPaymentTypeBinding;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.ui.profile.ProfileViewModel;


@AndroidEntryPoint
public class PaymentTypeFragment extends Fragment {

    private static final String TAG = "PaymentTypeFragment";

    private FragmentPaymentTypeBinding binding;

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

    private int TypePayment = 0;

    private Integer trajetByKm = 0;

    private PaymentTypeViewModel paymentTypeViewModel;

    private Integer UserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_type, container, false);
        paymentTypeViewModel = new ViewModelProvider(this).get(PaymentTypeViewModel.class);



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
        SelectedCarId = getArguments().getInt("SelectedCarId");

        trajetByKm = CalculationByDistance(Double.valueOf(LatitudeMapAdresseDepart),
                Double.valueOf(LongitudeMapAdresseDepart),
                Double.valueOf(LatitudeMapVilleDepart),
                Double.valueOf(LongitudeMapVilleArrive));

        paymentTypeViewModel.SelectUserFromRoom();
        paymentTypeViewModel.getUserRoom().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                UserId = user.getIdUser();
            }
        });


        setTypePayment();

        Suivant();
        goBack();
        return binding.getRoot();
    }

    public Integer CalculationByDistance(double lat1,double lon1,double lat2,double lon2) {
        int Radius = 6371;// radius of earth in Km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return kmInDec;
    }

    private void Suivant() {
        binding.SuivantBtnInPaymentTypeFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d(TAG, "offer: {"+
                "\n userId : "+UserId+
                "\n villeDepart : "+villeDepart+
                        "\n LatitudeMapVilleDepart : "+LatitudeMapVilleDepart+
                "\n LongitudeMapVilleDepart : "+LongitudeMapVilleDepart+

                 "\n adresseDepart : "+adresseDepart+
                 "\n LatitudeMapAdresseDepart : "+LatitudeMapAdresseDepart+
                 "\n LongitudeMapAdresseDepart : "+LongitudeMapAdresseDepart+

                 "\n villeArrive : "+villeArrive+
                 "\n LatitudeMapVilleArrive : "+LatitudeMapVilleArrive+
                 "\n LongitudeMapVilleArrive : "+LongitudeMapVilleArrive+

                 "\n dateAndHeureDepart : "+dateAndHeureDepart+
                 "\n dateAndHeureArrive : "+dateAndHeureArrive+
                 "\n trajetbyKm : "+trajetByKm+" Km"+

                 "\n villesStope : "+villesStope+
                 "\n nombreDesKilos : "+nombreDesKilos+
                 "\n priceParKilo : "+priceParKilo+
                 "\n SelectedCarId : "+SelectedCarId+
                 "\n TypePayment : "+TypePayment+
                 "\n status : Encours"+
                 "\n}");


                goToMesOffers(view);


            }
        });
    }

    private void goToMesOffers(View view) {

        Navigation.findNavController(view).navigate(R.id.action_paymentTypeFragment_to_mesOffesFragment2);

    }

    private void setTypePayment() {
        binding.paiementCartBanckRadionBtnInTypeFrag.setChecked(true);


        binding.paiementCartBanckRadionBtnInTypeFrag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TypePayment = 0;
                    binding.paiementCashkRadionBtnInTypeFrag.setChecked(false);
                }
            }
        });

        binding.paiementCashkRadionBtnInTypeFrag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TypePayment = 1;
                    binding.paiementCartBanckRadionBtnInTypeFrag.setChecked(false);
                }
            }
        });
    }

    private void goBack() {
        binding.GoBackIconInPaymentTypeFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}