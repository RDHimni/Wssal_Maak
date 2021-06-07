package ridaz.wssalmaak.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.FragmentProfileBinding;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.ui.updateprofile.UpdateProfileActivity;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);


        profileViewModel.SelectUserFromRoom();
        profileViewModel.getUserRoom().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {

                Glide.with(getContext()).load(user.getProfilePhotoUrl()).into(binding.profileImage);
                binding.userNameInMyProfile.setText(user.getFirstName() +" "+ user.getLastName());
                binding.ratingBarInMyProfile.setRating((float) user.getRate());
                binding.dateInscriptionInMyProfile.setText(user.getDateInscription());
                binding.PhoneNumberTvInMyProfile.setText(user.getPhoneNumber());
                binding.EmailTvInMyProfile.setText(user.getEmail());

                if (user.isPhoneValide()){
                    binding.CheckPhoneIconInMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_valide));
                }
                else {
                    binding.CheckPhoneIconInMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_croi));

                }
                if (user.isEmailValide()){
                    binding.ChecEmailIconInMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_valide));
                }
                else {
                    binding.ChecEmailIconInMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_croi));

                }
                if (user.isIdentityValide()){
                    binding.CheckIdentityIconInMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_valide));
                }
                else {
                    binding.CheckIdentityIconInMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_croi));

                }


            }
        });


        binding.ModifyProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Navigation.findNavController(binding.getRoot()).navigate(R.id.action_nav_profile_to_updateProfileFragment2);

                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }



}