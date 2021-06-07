package ridaz.wssalmaak.ui.mesdemands;

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
import ridaz.wssalmaak.databinding.MesDemandsFragmentBinding;

public class MesDemandsFragment extends Fragment {

    private MesDemandsViewModel mViewModel;
    private MesDemandsFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MesDemandsViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.mes_demands_fragment, container, false);

        binding.GoBackIconInMesDemandsFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }


}