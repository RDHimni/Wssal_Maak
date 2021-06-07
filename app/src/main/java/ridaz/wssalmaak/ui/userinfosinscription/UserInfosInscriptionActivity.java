package ridaz.wssalmaak.ui.userinfosinscription;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.Nullable;
import ridaz.wssalmaak.MainActivity;
import ridaz.wssalmaak.R;
import ridaz.wssalmaak.databinding.ActivityUserInfosInscriptionBinding;
import ridaz.wssalmaak.models.RequestModel;
import ridaz.wssalmaak.models.Respanse;
import ridaz.wssalmaak.models.User;

@AndroidEntryPoint
public class UserInfosInscriptionActivity extends AppCompatActivity {
    private static final String TAG = "UserInfosInscriptionAct";
    private String email, password, phoneNumber, Name, familyName, sex, dateBirth,dateInscription;
    private Bitmap bitmap;
    private Uri imageUri;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private UserInfosInscriptionRdViewModel viewModel;

    private ActivityUserInfosInscriptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_infos_inscription);
        binding.setLifecycleOwner(this);
        overridePendingTransition(R.anim.from_right_activity, R.anim.to_left_activity);

        viewModel = new ViewModelProvider(this).get(UserInfosInscriptionRdViewModel.class);




/*

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date c = calendar.getTime();
        dateInscription = curFormater.format(c);
        calendar.clear();



        final long today = MaterialDatePicker.todayInUtcMilliseconds();



        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("select Date");
        builder.setSelection(today);

        final MaterialDatePicker materialDatePicker = builder.build();
*/
        initDatePicker();

        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker();
                /*

                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        dateBirth = materialDatePicker.getHeaderText();
                        binding.editTextDate.setText(dateBirth);

                    }
                });

                 */


            }
        });


        binding.ImageUserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1, 1).start(UserInfosInscriptionActivity.this);

            }
        });


        binding.ConfirmerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int selectedSexID = binding.sexeTypeGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioBtn = binding.getRoot().findViewById(selectedSexID);
                sex = selectedRadioBtn.getText().toString();

                Name = binding.editTextUserName.getText().toString();
                familyName = binding.editTextUsrFamelyName.getText().toString();
                Intent intent = getIntent();
                phoneNumber = intent.getStringExtra("phoneNumber");
                email = intent.getStringExtra("email");
                password = intent.getStringExtra("password");
                dateInscription = getTodaysDate();

                if (bitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
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
                    Toast.makeText(UserInfosInscriptionActivity.this, "you must select image", Toast.LENGTH_SHORT).show();
                }



                viewModel.getResult().observe(UserInfosInscriptionActivity.this, new Observer<Respanse>() {
                    @Override
                    public void onChanged(Respanse respanse) {





                        if (respanse.isStatus()){

                            Log.d(TAG, "rida onChanged: "+respanse.getRemarks());
                            Toast.makeText(UserInfosInscriptionActivity.this, respanse.getRemarks(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "rida onChanged: "+respanse.getUser().toString());


                            viewModel.insertUserInRoom(respanse.getUser());

                            viewModel.SelectUserFromRoom();
                            viewModel.getUserRoom().observe(UserInfosInscriptionActivity.this, new Observer<User>() {
                                @Override
                                public void onChanged(User user) {
                                    Log.d(TAG, "rida room onChanged: "+ user.toString());
                                }
                            });

                            Intent goMainActivity = new Intent(UserInfosInscriptionActivity.this, MainActivity.class);
                            startActivity(goMainActivity);
                            finish();

                        }
                        else {

                            Toast.makeText(UserInfosInscriptionActivity.this, "Connexion faible réessayer  ", Toast.LENGTH_SHORT).show();

                        }


                    }
                });








            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);


            imageUri = result.getUri();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                binding.profileImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Glide.with(this).load(url).into(profileImageView);
            // profileImageView.setImageURI(imageUri);

        } else {
            Toast.makeText(getApplicationContext(), "Error, Try again", Toast.LENGTH_SHORT).show();
        }

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

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    public void openDatePicker()
    {
        datePickerDialog.show();
    }
}