package ridaz.wssalmaak.ui.notification;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.NotificationFragmentBinding;

@AndroidEntryPoint
public class NotificationFragment extends Fragment {

    private NotificationViewModel mViewModel;

    private NotificationFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.notification_fragment, container, false);

        binding.GoBackIconInNotificationFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }



}