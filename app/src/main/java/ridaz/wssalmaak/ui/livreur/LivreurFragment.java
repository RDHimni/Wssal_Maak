package ridaz.wssalmaak.ui.livreur;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentLivreurBinding;

public class LivreurFragment extends Fragment {

    private LivreurViewModel livreurViewModel;

    private FragmentLivreurBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        livreurViewModel = new ViewModelProvider(this).get(LivreurViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_livreur, container, false);

        binding.NotificationRlInDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_livreur_to_notificationFragment);

            }
        });

        binding.MesOffersBnInClientFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_livreur_to_mesOffesFragment);
            }
        });

        binding.CreeOffersBnInClientFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_livreur_to_villeDepartFragment);
            }
        });
        return binding.getRoot();
    }
}