package ridaz.wssalmaak.ui.connexionemailpassword;

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
import ridaz.wssalmaak.databinding.EmailPasswordConnexionRdFragmentBinding;

public class EmailPasswordConnexionRdFragment extends Fragment {


    private EmailPasswordConnexionRdFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.email_password_connexion_rd_fragment, container, false);

        return binding.getRoot();
    }


}