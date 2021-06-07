package ridaz.wssalmaak.ui.userinfosinscription;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import ridaz.wssalmaak.MainActivity;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.constants.Constants;
import ridaz.wssalmaak.databinding.UserInfosInscriptionRdFragmentBinding;
import ridaz.wssalmaak.models.Respanse;
import ridaz.wssalmaak.models.User;


@AndroidEntryPoint
public class UserInfosInscriptionRdFragment extends Fragment {

    private static final String TAG = "UserInfosInscriptionRdF";

    private String email,password,phoneNumber,Name,familyName,sex,dateBirth,dateInscription;

    private UserInfosInscriptionRdFragmentBinding binding;

    private Bitmap mybitmap;
    private DatePickerDialog datePickerDialog;

    private UserInfosInscriptionRdViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_infos_inscription_rd_fragment, container, false);
        viewModel = new ViewModelProvider(this).get(UserInfosInscriptionRdViewModel.class);

        email = getArguments().getString("email");
        password = getArguments().getString("password");
        phoneNumber = getArguments().getString("phoneNumber");

        Constants.bitmapProfile.observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {

                mybitmap = bitmap;
                binding.profileImageInUFIF.setImageBitmap(mybitmap);
            }
        });



        initDatePicker();

        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });


        binding.profileImageInUFIF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1,1).start(getActivity());

            }
        });


        binding.ConfirmerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = binding.editTextUserName.getText().toString();
                familyName = binding.editTextUsrFamelyName.getText().toString();
                int selectedSexID =  binding.sexeTypeGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioBtn = binding.getRoot().findViewById(selectedSexID);
                sex = selectedRadioBtn.getText().toString();
                dateInscription = getTodaysDate();




                if (mybitmap != null) {

                    Log.d(TAG, "rida onClick: " + mybitmap);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    mybitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
                    byte[] imageInByte = byteArrayOutputStream.toByteArray();
                    String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                    //Log.d(TAG, "rida uploadImage: " + encodedImage);

                    //viewModel.uploadImage(encodedImage);

                    viewModel.InsertUserInDb2(
                            Name,
                            familyName,
                            phoneNumber,
                            email,
                            password,
                            dateBirth,
                            sex,
                            encodedImage,
                            "sfdlljsdlslflkskjAKJ0998I",
                            dateInscription
                    );



                }
                else {
                    Toast.makeText(getContext(), "Voulez sélectionnez une image", Toast.LENGTH_SHORT).show();
                }



                viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Respanse>() {
                    @Override
                    public void onChanged(Respanse respanse) {

                        if (respanse.isStatus()){

                            Log.d(TAG, "rida onChanged: "+respanse.getRemarks());
                            Toast.makeText(getContext(), respanse.getRemarks(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "rida onChanged: "+respanse.getUser().toString());


                            viewModel.insertUserInRoom(respanse.getUser());

                            viewModel.SelectUserFromRoom();
                            viewModel.getUserRoom().observe(getViewLifecycleOwner(), new Observer<User>() {
                                @Override
                                public void onChanged(User user) {
                                    Log.d(TAG, "rida room onChanged: "+ user.toString());
                                }
                            });

                            Intent goMainActivity = new Intent(getContext(), MainActivity.class);
                            startActivity(goMainActivity);
                            getActivity().finish();

                        }
                        else {

                            Toast.makeText(getContext(), "Connexion faible réessayer  ", Toast.LENGTH_SHORT).show();

                        }


                    }
                });



            }
        });



        return binding.getRoot();
    }




    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day +"/"+ month+"/"+ year;
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String d = day +"/"+month+"/"+year;
                binding.editTextDate.setText(d);
                dateBirth = d;
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void openDatePicker()
    {
        datePickerDialog.show();
    }
}