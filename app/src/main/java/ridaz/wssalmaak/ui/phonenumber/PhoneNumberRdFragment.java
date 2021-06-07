package ridaz.wssalmaak.ui.phonenumber;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import ridaz.wssalmaak.MainActivity;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.PhoneNumberRdFragmentBinding;

public class PhoneNumberRdFragment extends Fragment {

    private static final String TAG = "PhoneNumberFragment";
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    private PhoneNumberRdFragmentBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.phone_number_rd_fragment, container, false);

        auth = FirebaseAuth.getInstance();

        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d(TAG, "aly onVerificationFailed: " + e.getMessage());
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Log.d(TAG, "aly onCodeSent: " + "OTP has been Sent");
                Toast.makeText(getContext(), "OTP has been Sent", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String country_code = "212";
                        String phone = binding.editTextPhone.getText().toString();
                        String phoneNumber = "+" + country_code + "" + phone;

                        Bundle bundle = new Bundle();
                        bundle.putString("phoneNumber",phoneNumber);
                        bundle.putString("auth", s);

                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneNumberRdFragment_to_codeOTPRdFragment,bundle);

                    }
                },1000);


            }
        };


        binding.getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCodeBtnOnclick();
            }
        });


        return binding.getRoot();
    }

    public void getCodeBtnOnclick() {

        String country_code = "212";
        String phone = binding.editTextPhone.getText().toString();
        String phoneNumber = "+" + country_code + "" + phone;

        if (!country_code.isEmpty() || !phone.isEmpty()){
            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phoneNumber)
                    .setTimeout(60L , TimeUnit.SECONDS)
                    .setActivity(getActivity())
                    .setCallbacks(mCallBacks)
                    .build();
            PhoneAuthProvider.verifyPhoneNumber(options);

        }else{

            Toast.makeText(getContext(), "Please Enter Country Code and Phone Number", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user !=null){
            sendToMain();
        }
    }

    private void sendToMain(){
        Intent mainIntent = new Intent(getContext() , MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }


}

