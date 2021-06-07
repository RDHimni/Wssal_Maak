package ridaz.wssalmaak.ui.emailpasswordinscription;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.EmailPasswordInscriptionRdFragmentBinding;
import ridaz.wssalmaak.ui.userinfosinscription.UserInfosInscriptionActivity;

public class EmailPasswordInscriptionRdFragment extends Fragment {


    private String phoneNumber;

   private EmailPasswordInscriptionRdFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.email_password_inscription_rd_fragment, container, false);
        phoneNumber =getArguments().getString("phoneNumber");


        binding.SuivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.EmailEditTxt.getText().toString();
                String password = binding.PasswordEditTxt.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("phoneNumber",phoneNumber);
                bundle.putString("email",email);
                bundle.putString("password",password);

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_emailPasswordInscriptionRdFragment_to_userInfosInscriptionRdFragment,bundle);

                /*
                Intent intent = new Intent(getActivity(), UserInfosInscriptionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();

                 */

            }
        });
        return binding.getRoot();
    }


}