package ridaz.wssalmaak.constants;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

/**
 * Rida Dhimni
 * 18/04/2021
 **/

public class Constants {

    //ProfileImage
    static public MutableLiveData<Bitmap> bitmapProfile = new MutableLiveData<>();
    static public boolean isProfileImage = false;

    //CINFace
    static public MutableLiveData<Bitmap> bitmapCINFace = new MutableLiveData<>();
    static public boolean isCINFace = false;

    //CINBack
    static public MutableLiveData<Bitmap> bitmapCINBack = new MutableLiveData<>();
    static public boolean isCINBack = false;

    //Passport
    static public MutableLiveData<Bitmap> bitmapPassport = new MutableLiveData<>();
    static public boolean isPassport = false;
}