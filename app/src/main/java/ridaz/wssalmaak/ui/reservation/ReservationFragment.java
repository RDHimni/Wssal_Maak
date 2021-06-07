package ridaz.wssalmaak.ui.reservation;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.converters.Converters;
import ridaz.wssalmaak.databinding.ReservationFragmentBinding;
import ridaz.wssalmaak.models.Offer;

public class ReservationFragment extends Fragment {

    private ReservationViewModel mViewModel;
    private ReservationFragmentBinding binding;

    private Offer offer;

    public ReservationFragment() {
    }

    public ReservationFragment(Offer offer) {
        this.offer = offer;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.reservation_fragment, container, false);


        offer = Converters.fromStringToOffer(getArguments().getString("OfferString"));


        binding.reserverBtnInResevationFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_reservationFragment2_to_confirmationReservationFragment);
            }
        });

        binding.GoBackIconInReservationFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }

}