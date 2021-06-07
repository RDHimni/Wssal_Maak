package ridaz.wssalmaak.ui.paymenttype;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentPaymentTypeBinding;


public class PaymentTypeFragment extends Fragment {

    private static final String TAG = "PaymentTypeFragment";

    private FragmentPaymentTypeBinding binding;

    private int TypePayment = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_type, container, false);
        goBack();

        setTypePayment();

        Suivant();

        return binding.getRoot();
    }

    private void Suivant() {
        binding.SuivantBtnInPaymentTypeFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), ""+TypePayment, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTypePayment() {
        binding.paiementCartBanckRadionBtnInTypeFrag.setChecked(true);


        binding.paiementCartBanckRadionBtnInTypeFrag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TypePayment = 0;
                    binding.paiementCashkRadionBtnInTypeFrag.setChecked(false);
                }
            }
        });

        binding.paiementCashkRadionBtnInTypeFrag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    TypePayment = 1;
                    binding.paiementCartBanckRadionBtnInTypeFrag.setChecked(false);
                }
            }
        });
    }

    private void goBack() {
        binding.GoBackIconInPaymentTypeFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}