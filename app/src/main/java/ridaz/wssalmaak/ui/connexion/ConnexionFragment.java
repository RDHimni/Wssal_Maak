package ridaz.wssalmaak.ui.connexion;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

import ridaz.wssalmaak.MainActivity;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentConnexionBinding;


public class ConnexionFragment extends Fragment {

    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    private FragmentConnexionBinding binding;

    public ConnexionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_connexion, container, false);


         auth = FirebaseAuth.getInstance();
         FirebaseUser user = auth.getCurrentUser();

         binding.connexionEmailBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        if (user !=null){
        sendToMain();
        }
        else {
        //Navigation.findNavController(view).navigate(R.id.action_connexionFragment_to_emailPasswordConnexionFragment);
        }
        }
        });

         binding.inscriptionBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        if (user !=null){
        sendToMain();
        }
        else {
       // Navigation.findNavController(view).navigate(R.id.action_connexionFragment_to_userInfosInscriptionFragment2);
        }
        }
        });



         return binding.getRoot();



    }

    private void sendToMain(){
        Intent mainIntent = new Intent(getContext() , MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}