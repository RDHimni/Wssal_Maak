package ridaz.wssalmaak.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import ridaz.wssalmaak.models.Offer;

/**
 * Rida Dhimni
 * 01/05/2021
 **/

public class Converters {

    @TypeConverter
    public static String fromOfferToString(Offer offer) {
        return new Gson().toJson(offer);
    }

    @TypeConverter
    public static Offer fromStringToOffer(String s) {
        return new Gson().fromJson(s,Offer.class);
    }

}