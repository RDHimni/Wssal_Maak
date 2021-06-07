package ridaz.wssalmaak.ui.updateprofile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.PageViewAdapter;
import ridaz.wssalmaak.databinding.UpdateProfileFragmentBinding;

public class UpdateProfileFragment extends Fragment {

    private UpdateProfileViewModel mViewModel;
    private UpdateProfileFragmentBinding binding;

    public UpdateProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(UpdateProfileViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.update_profile_fragment, container, false);


        final PageViewAdapter pageViewAdapter = new PageViewAdapter(getContext(), 2, getActivity().getSupportFragmentManager());
        binding.viewPagerInUpdateProfile.setAdapter(pageViewAdapter);
        binding.viewPagerInUpdateProfile.setCurrentItem(0);

        binding.PersonnellesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.viewPagerInUpdateProfile.setCurrentItem(0);
            }
        });

        binding.CarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.viewPagerInUpdateProfile.setCurrentItem(1);
            }
        });


        binding.viewPagerInUpdateProfile.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                       binding.PersonnellesBtn.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                       binding.PersonnellesBtn.setTextColor(getResources().getColor(R.color.yellow_200));
                       binding.CarBtn.setBackground(getResources().getDrawable(R.drawable.button_bg));
                       binding.CarBtn.setTextColor(getResources().getColor(R.color.blue_700));
                        break;
                    case 1:
                        binding.PersonnellesBtn.setBackground(getResources().getDrawable(R.drawable.button_bg));
                        binding.PersonnellesBtn.setTextColor(getResources().getColor(R.color.blue_700));
                        binding.CarBtn.setBackground(getResources().getDrawable(R.drawable.button_bg_slected));
                        binding.CarBtn.setTextColor(getResources().getColor(R.color.yellow_200));

                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });









        binding.GoBackIconInUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateProfileFragment2_to_nav_profile);

                getActivity().onBackPressed();
            }
        });






        return binding.getRoot();
    }



}