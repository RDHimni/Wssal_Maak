package ridaz.wssalmaak.ui.searchoffer;

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
import ridaz.wssalmaak.databinding.SearchOfferFragmentBinding;

public class SearchOfferFragment extends Fragment {

    private SearchOfferViewModel mViewModel;
    private SearchOfferFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SearchOfferViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.search_offer_fragment, container, false);

        binding.GoBackIconInSearchofferFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }


}