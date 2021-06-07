package ridaz.wssalmaak.ui.mesoffer;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.OfferAdapter;
import ridaz.wssalmaak.converters.Converters;
import ridaz.wssalmaak.databinding.MesOffesFragmentBinding;
import ridaz.wssalmaak.models.Car;
import ridaz.wssalmaak.models.Offer;
import ridaz.wssalmaak.models.User;

public class MesOffesFragment extends Fragment {

    private MesOffesViewModel mViewModel;

    private MesOffesFragmentBinding binding;

    private OfferAdapter offerAdapterEncours,offerAdapterAnnulees,offerAdapterTerminee;
    private ArrayList<Offer> listOffersEncours, listOffersAnnulees, listOffersTerminee;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MesOffesViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.mes_offes_fragment, container, false);
        BtnClickHandel();
        RetrieveData();


        return binding.getRoot();
    }

    private void RetrieveData() {
        listOffersEncours = new ArrayList<>();
        listOffersAnnulees = new ArrayList<>();
        listOffersTerminee = new ArrayList<>();

        Offer offer = new Offer(
                "villeDepart",
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
                new Car("1234", "", "", "ALFA-ROMEO", "Voiture W", "12/03/23", "-16711680"),
                new User(),
                1,
                0.5,
                30,
                "sale;Casablanca", 0

        );
        Offer offer2 = new Offer(
                "villeDepart",
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
                new Car("1234", "", "", "ALFA-ROMEO", "Voiture W", "12/03/23", "-16711680"),
                new User(),
                1,
                0.5,
                30,
                "sale;Casablanca", 1

        );
        Offer offer3 = new Offer(
                "villeDepart",
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
                new Car("1234", "", "", "ALFA-ROMEO", "Voiture W", "12/03/23", "-16711680"),
                new User(),
                1,
                0.5,
                30,
                "",
                1

        );

        Offer offer4 = new Offer(
                "villeDepart",
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
                new Car("1234", "", "", "ALFA-ROMEO", "Voiture W", "12/03/23", "-16711680"),
                new User(),
                1,
                0.5,
                30,
                "",
                0

        );

        listOffersEncours.add(offer3);
        listOffersEncours.add(offer2);
        listOffersEncours.add(offer3);
        listOffersAnnulees.add(offer4);
        listOffersAnnulees.add(offer2);
        listOffersTerminee.add(offer);
        listOffersAnnulees.add(offer4);
        listOffersTerminee.add(offer);

        offerAdapterEncours = new OfferAdapter(getContext(), listOffersEncours);
        binding.ListEcoursInMesOffersFragment.setAdapter(offerAdapterEncours);
        binding.ListEcoursInMesOffersFragment.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        offerAdapterAnnulees = new OfferAdapter(getContext(), listOffersAnnulees);
        binding.ListAnnuleesInMesOffersFragment.setAdapter(offerAdapterAnnulees);
        binding.ListAnnuleesInMesOffersFragment.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        offerAdapterTerminee = new OfferAdapter(getContext(), listOffersTerminee);
        binding.ListTermineeInMesOffersFragment.setAdapter(offerAdapterTerminee);
        binding.ListTermineeInMesOffersFragment.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        offerAdapterEncours.setOnItemClickListener(new OfferAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {

                /*
                Bundle bundle = new Bundle();
                String offerString = Converters.fromOfferToString(listOffers.get(position));
                bundle.putString("OfferString",offerString);

                Navigation.findNavController(itemView).navigate(R.id.action_nav_client_to_reservationFragment2,bundle);

                 */

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

    private void BtnClickHandel() {
        binding.EnCoursBtnInMesOfferFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.EnCoursBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                binding.EnCoursBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.yellow_200));

                binding.AnnuleesBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                binding.AnnuleesBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.gray));

                binding.TermineesBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                binding.TermineesBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.gray));

                binding.ListEcoursInMesOffersFragment.setVisibility(View.VISIBLE);
                binding.ListAnnuleesInMesOffersFragment.setVisibility(View.GONE);
                binding.ListTermineeInMesOffersFragment.setVisibility(View.GONE);

            }
        });
        binding.AnnuleesBtnInMesOfferFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.EnCoursBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                binding.EnCoursBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.gray));

                binding.AnnuleesBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                binding.AnnuleesBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.yellow_200));

                binding.TermineesBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                binding.TermineesBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.gray));

                binding.ListEcoursInMesOffersFragment.setVisibility(View.GONE);
                binding.ListAnnuleesInMesOffersFragment.setVisibility(View.VISIBLE);
                binding.ListTermineeInMesOffersFragment.setVisibility(View.GONE);
            }
        });

        binding.TermineesBtnInMesOfferFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.EnCoursBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                binding.EnCoursBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.gray));

                binding.AnnuleesBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_hided));
                binding.AnnuleesBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.gray));

                binding.TermineesBtnInMesOfferFragments.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                binding.TermineesBtnInMesOfferFragments.setTextColor(getResources().getColor(R.color.yellow_200));

                binding.ListEcoursInMesOffersFragment.setVisibility(View.GONE);
                binding.ListAnnuleesInMesOffersFragment.setVisibility(View.GONE);
                binding.ListTermineeInMesOffersFragment.setVisibility(View.VISIBLE);
            }
        });

        binding.GoBackIconInMesOffersFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


}