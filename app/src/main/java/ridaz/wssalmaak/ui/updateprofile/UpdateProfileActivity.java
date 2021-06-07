package ridaz.wssalmaak.ui.updateprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.Nullable;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.adapters.PageViewAdapter;
import ridaz.wssalmaak.constants.Constants;
import ridaz.wssalmaak.databinding.ActivityUpdateProfileBinding;

@AndroidEntryPoint
public class UpdateProfileActivity extends AppCompatActivity {

    private ActivityUpdateProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update_profile);
        binding.setLifecycleOwner(this);

        viewPagerSetUp();
        goBackIconOnclick();


    }

    private void goBackIconOnclick() {
        binding.GoBackIconInUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateProfileFragment2_to_nav_profile);

                onBackPressed();
            }
        });
    }

    private void viewPagerSetUp() {
        final PageViewAdapter pageViewAdapter = new PageViewAdapter(this, 2, getSupportFragmentManager());
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
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            Uri imageUri = result.getUri();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                if (Constants.isProfileImage){
                    Constants.bitmapProfile.setValue(bitmap);
                }
                else {
                    if (Constants.isCINFace){
                        Constants.bitmapCINFace.setValue(bitmap);


                    }else {
                        if (Constants.isCINBack){
                            Constants.bitmapCINBack.setValue(bitmap);

                        }
                        else {
                            if (Constants.isPassport){
                                Constants.bitmapPassport.setValue(bitmap);

                            }
                        }
                    }

                }


                //binding.profileImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Glide.with(this).load(url).into(profileImageView);
            // profileImageView.setImageURI(imageUri);

        } else {
            Toast.makeText(getApplicationContext(), "Error, Try again", Toast.LENGTH_SHORT).show();
        }

    }

}