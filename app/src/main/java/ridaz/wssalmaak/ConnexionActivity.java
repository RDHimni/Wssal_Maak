package ridaz.wssalmaak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.Nullable;
import ridaz.wssalmaak.constants.Constants;


@AndroidEntryPoint
public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);


           Uri imageUri = result.getUri();

            try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Constants.bitmapProfile.setValue(bitmap);
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