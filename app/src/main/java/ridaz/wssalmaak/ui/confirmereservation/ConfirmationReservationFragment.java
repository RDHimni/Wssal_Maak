package ridaz.wssalmaak.ui.confirmereservation;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.ConfirmationReservationFragmentBinding;

public class ConfirmationReservationFragment extends Fragment {

    private ConfirmationReservationViewModel mViewModel;

    private ConfirmationReservationFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ConfirmationReservationViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.confirmation_reservation_fragment, container, false);


        binding.GoBackIconInConfirmationReservationFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        return binding.getRoot();
    }



}