package ridaz.wssalmaak.ui.codeotp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentCodeOTPRdBinding;


public class CodeOTPRdFragment extends Fragment {

    private static final String TAG = "CodeOTPFragment";

    private String OTP, phoneNumber;
    private FirebaseAuth firebaseAuth;

    private FragmentCodeOTPRdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_code_o_t_p_rd, container, false);
        firebaseAuth = FirebaseAuth.getInstance();



        OTP =  getArguments().getString("auth");
        phoneNumber =getArguments().getString("phoneNumber");


        binding.VerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyBtnOnclick();
            }
        });

        binding.ResendCodetextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResendCodeTextViewOnclick();
            }
        });

        numberMove();

        return binding.getRoot();
    }

    public void VerifyBtnOnclick() {

        //String verification_code = binding.editTextCodeOTP.getText().toString();

            String verification_code = getVerification_code();

            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP, verification_code);
            signIn(credential);

    }

    private String getVerification_code() {


        String verification_code = "";

        String inputOtp1 = binding.inputOtp1.getText().toString();
        String inputOtp2 = binding.inputOtp2.getText().toString();
        String inputOtp3 = binding.inputOtp3.getText().toString();
        String inputOtp4 = binding.inputOtp4.getText().toString();
        String inputOtp5 = binding.inputOtp5.getText().toString();
        String inputOtp6 = binding.inputOtp6.getText().toString();

        if (!inputOtp1.trim().isEmpty() && !inputOtp2.trim().isEmpty() && !inputOtp3.trim().isEmpty() && !inputOtp4.trim().isEmpty() &&
                !inputOtp5.trim().isEmpty() && !inputOtp6.trim().isEmpty()) {

             verification_code = inputOtp1 + inputOtp2 + inputOtp3 + inputOtp4 + inputOtp5 + inputOtp6;
        }
        else {
            Toast.makeText(getContext(), "Veuillez entrer OTP", Toast.LENGTH_SHORT).show();

        }

        return verification_code;
    }

    public void numberMove(){

        binding.inputOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
              if (!s.toString().trim().isEmpty()){
                  binding.inputOtp2.requestFocus();
              }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputOtp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputOtp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.inputOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void signIn(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    sendToEmailPasswordInscriptionActivity();
                } else {
                    Toast.makeText(getContext(), "Verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendToEmailPasswordInscriptionActivity() {

        Bundle bundle = new Bundle();
        bundle.putString("phoneNumber",phoneNumber);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_codeOTPRdFragment_to_emailPasswordInscriptionRdFragment,bundle);

    }


    public void ResendCodeTextViewOnclick() {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(getActivity())
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        //  signIn(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.d(TAG, "aly onVerificationFailed: " + e.getMessage());

                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);

                        //sometime the code is not detected automatically
                        //so user has to manually enter the code


                        Log.d(TAG, "aly onCodeSent: " + "OTP has been Sent");

                        Toast.makeText(getContext(), "OTP has been Sent", Toast.LENGTH_SHORT).show();


                    }

                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

}