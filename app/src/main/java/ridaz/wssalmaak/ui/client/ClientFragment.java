package ridaz.wssalmaak.ui.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.OfferAdapter;
import ridaz.wssalmaak.converters.Converters;
import ridaz.wssalmaak.databinding.FragmentClientBinding;
import ridaz.wssalmaak.models.Car;
import ridaz.wssalmaak.models.Offer;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.ui.reservation.ReservationFragment;

@AndroidEntryPoint
public class ClientFragment extends Fragment {

    private static final String TAG = "ClientFragment";

    private ClientViewModel clientViewModel;
    private FragmentClientBinding binding;

    private OfferAdapter offerAdapter;
    private ArrayList<Offer> listOffers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_client, container, false);
        RetrieveData();

        binding.MesDemandesBnInClientFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_client_to_mesDemandsFragment);
            }
        });




        binding.searchOfferInClientFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_client_to_searchOfferFragment);
            }
        });

        binding.NotificationRlInPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_client_to_notificationFragment);
            }
        });

        binding.NotificationIconInPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_client_to_notificationFragment);
            }
        });
        return binding.getRoot();
    }

    private void RetrieveData() {
        listOffers = new ArrayList<>();

        Offer offer = new Offer(
                "villeDepart",
                "LatitudeMapVilleDepart",
                "LatitudeMapVilleDepart",
                "adresseDepart",
                "LatitudeMapAdresseDepart",
                "LatitudeMapAdresseDepart",
                "villeArriver",
                "LatitudeMapvilleArriver",
                "LatitudeMapvilleArriver",
                "dateDepart",
                "dateArriver",
                "tempsDepart",
                "tempsArriver",
                "trajetParKm",
                new Car("1234","","","ALFA-ROMEO","Voiture W","12/03/23","-16711680"),
                new User(),
                1,
                0.5,
                30,
                "sale;Casablanca",
                0
        );
        Offer offer2 = new Offer(
                "villeDepart",
                "LatitudeMapVilleDepart",
                "LatitudeMapVilleDepart",
                "adresseDepart",
                "LatitudeMapAdresseDepart",
                "LatitudeMapAdresseDepart",
                "villeArriver",
                "LatitudeMapvilleArriver",
                "LatitudeMapvilleArriver",
                "dateDepart",
                "dateArriver",
                "tempsDepart",
                "tempsArriver",
                "trajetParKm",
                new Car("1234","","","ALFA-ROMEO","Voiture W","12/03/23","-16711680"),
                new User(),
                1,
                0.5,
                30,
                "sale;Casablanca", 1

        );
        Offer offer3 = new Offer(
                "villeDepart",
                "adresseDepart",
                "LatitudeMapVilleDepart",
                "LatitudeMapVilleDepart",
                "LatitudeMapAdresseDepart",
                "LatitudeMapAdresseDepart",
                "villeArriver",
                "LatitudeMapvilleArriver",
                "LatitudeMapvilleArriver",
                "dateDepart",
                "dateArriver",
                "tempsDepart",
                "tempsArriver",
                "trajetParKm",
                new Car("1234","","","ALFA-ROMEO","Voiture W","12/03/23","-16711680"),
                new User(),
                1,
                0.5,
                30,
                "",
                1

        );

        Offer offer4 = new Offer(
                "villeDepart",
                "LatitudeMapVilleDepart",
                "LatitudeMapVilleDepart",
                "adresseDepart",
                "LatitudeMapAdresseDepart",
                "LatitudeMapAdresseDepart",
                "villeArriver",
                "LatitudeMapvilleArriver",
                "LatitudeMapvilleArriver",
                "dateDepart",
                "dateArriver",
                "tempsDepart",
                "tempsArriver",
                "trajetParKm",
                new Car("1234","","","ALFA-ROMEO","Voiture W","12/03/23","-16711680"),
                new User(),
                1,
                0.5,
                30,
                "",
                0

        );

        listOffers.add(offer3);
        listOffers.add(offer2);
        listOffers.add(offer3);
        listOffers.add(offer4);
        listOffers.add(offer2);
        listOffers.add(offer);
        listOffers.add(offer4);
        listOffers.add(offer);

        offerAdapter = new OfferAdapter(getContext(),listOffers);
        binding.ListOffersInClientFragment.setAdapter(offerAdapter);
        binding.ListOffersInClientFragment.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));


        offerAdapter.setOnItemClickListener(new OfferAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                Bundle bundle = new Bundle();
                String offerString = Converters.fromOfferToString(listOffers.get(position));
                bundle.putString("OfferString",offerString);

                Navigation.findNavController(itemView).navigate(R.id.action_nav_client_to_reservationFragment2,bundle);

                /**
                ReservationFragment reservationFragment = new ReservationFragment(listOffers.get(position));
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                transaction.replace(R.id.nav_host_fragment,reservationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                 */


            }
        });
    }
}