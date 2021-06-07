package ridaz.wssalmaak.ui.updateprofile.personne;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.constants.Constants;
import ridaz.wssalmaak.databinding.PersonneFragmentBinding;
import ridaz.wssalmaak.models.User;

@AndroidEntryPoint
public class PersonneFragment extends Fragment {

    private static final String TAG = "PersonneFragment";

    private PersonneViewModel mViewModel;

    private PersonneFragmentBinding binding;

    private Bitmap myProfileBitmap = null, myCINFaceBitmap = null, myCINBackBitmap = null, myPassportBitmap = null;
    private String email, password, phoneNumber, Name, familyName, sex, dateBirth;
    private DatePickerDialog datePickerDialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.personne_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(PersonneViewModel.class);


        mViewModel.SelectUserFromRoom();
        mViewModel.getUserRoom().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {

                Log.d(TAG, "rida onChanged: "+ user.getBeardDate());
                Glide.with(getContext()).load(user.getProfilePhotoUrl()).into(binding.profileImageInProfileUpdate);
                binding.editTextUserNameInProfileUpdate.setText(user.getFirstName());
                binding.editTextUserFamilyNameInProfileUpdate.setText(user.getLastName());
                binding.editTextUserPhoneNumberInProfileUpdate.setText(user.getPhoneNumber().substring(4));
                binding.editTextUserEmailInProfileUpdate.setText(user.getEmail());
                binding.editTextUserPasswordInProfileUpdate.setText(user.getPassword());
                initDatePicker(user.getBeardDate());

                String s = user.getSexion();
                if (s.trim().equals("Homme")) binding.radioManBtnInProfileUpdate.setChecked(true);
                else binding.radioWomanBtnInProfileUpdate.setChecked(true);

                if (!user.getCartIdentiteFrontUrl().trim().isEmpty())
                    Glide.with(getContext()).load(user.getCartIdentiteFrontUrl()).into(binding.CINFaceMvInProfileUpdate);

                if (!user.getCartIdentiteBackUrl().trim().isEmpty())
                    Glide.with(getContext()).load(user.getCartIdentiteBackUrl()).into(binding.CINBackMvInProfileUpdate);

                if (!user.getPassportUrl().trim().isEmpty())
                    Glide.with(getContext()).load(user.getPassportUrl()).into(binding.PassportMvInProfileUpdate);


            }
        });


        PickImages();
        PickDateBirth();
        getSexion();

        EnregisterOnclick();

        AnnlulerBtnOnclick();

        return binding.getRoot();
    }

    private void AnnlulerBtnOnclick() {
        binding.AnnulerBtnInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void EnregisterOnclick() {
        binding.EnregistrerBtnInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = binding.editTextUserNameInProfileUpdate.getText().toString();
                familyName = binding.editTextUserFamilyNameInProfileUpdate.getText().toString();
                phoneNumber = binding.editTextUserPhoneNumberInProfileUpdate.getText().toString();
                email = binding.editTextUserEmailInProfileUpdate.getText().toString();
                password = binding.editTextUserPasswordInProfileUpdate.getText().toString();


            }
        });
    }

    private void getSexion() {
        int selectedSexID = binding.sexeTypeGroupInProfileUpdate.getCheckedRadioButtonId();
        RadioButton selectedRadioBtn = binding.getRoot().findViewById(selectedSexID);
        sex = selectedRadioBtn.getText().toString();
    }

    private void PickDateBirth() {
        binding.DateBtnInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
    }

    private void PickImages() {
        Constants.bitmapProfile.observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                myProfileBitmap = bitmap;
                binding.profileImageInProfileUpdate.setImageBitmap(bitmap);
            }
        });

        Constants.bitmapCINFace.observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                myCINFaceBitmap = bitmap;
                binding.CINFaceMvInProfileUpdate.setImageBitmap(bitmap);
            }
        });

        Constants.bitmapCINBack.observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                myCINBackBitmap = bitmap;
                binding.CINBackMvInProfileUpdate.setImageBitmap(bitmap);
            }
        });

        Constants.bitmapPassport.observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                myPassportBitmap = bitmap;
                binding.PassportMvInProfileUpdate.setImageBitmap(bitmap);
            }
        });

        binding.profileImageInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.isProfileImage = true;
                Constants.isCINFace = false;
                Constants.isCINBack = false;
                Constants.isPassport = false;

                CropImage.activity()
                        .setGuidelinesColor(getResources().getColor(R.color.yellow_200))
                        .setActivityMenuIconColor(getResources().getColor(R.color.yellow_200))
                        .setAspectRatio(1, 1).start(getActivity());

            }
        });

        binding.CINFaceCvInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.isProfileImage = false;
                Constants.isCINFace = true;
                Constants.isCINBack = false;
                Constants.isPassport = false;

                CropImage.activity()
                        .setGuidelinesColor(getResources().getColor(R.color.yellow_200))
                        .setActivityMenuIconColor(getResources().getColor(R.color.yellow_200))
                        .setAspectRatio(2, 1).start(getActivity());

            }
        });

        binding.CINBackCvInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.isProfileImage = false;
                Constants.isCINFace = false;
                Constants.isCINBack = true;
                Constants.isPassport = false;

                CropImage.activity()
                        .setGuidelinesColor(getResources().getColor(R.color.yellow_200))
                        .setActivityMenuIconColor(getResources().getColor(R.color.yellow_200))
                        .setAspectRatio(2, 1).start(getActivity());

            }
        });

        binding.PassportCvInProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.isProfileImage = false;
                Constants.isCINFace = false;
                Constants.isCINBack = false;
                Constants.isPassport = true;
                CropImage.activity()
                        .setGuidelinesColor(getResources().getColor(R.color.yellow_200))
                        .setActivityMenuIconColor(getResources().getColor(R.color.yellow_200))
                        .setAspectRatio(1, 2).start(getActivity());

            }
        });
    }

    private void initDatePicker(String birthdate) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String d = day + "/" + month + "/" + year;
                binding.DateBtnInProfileUpdate.setText(d);
                dateBirth = d;
            }
        };


        String[] s = birthdate.split("/");

        int d = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, y, m, d);
        binding.DateBtnInProfileUpdate.setText(birthdate);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void openDatePicker() {
        datePickerDialog.show();
    }
}